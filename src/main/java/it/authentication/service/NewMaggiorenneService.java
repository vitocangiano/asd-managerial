package it.authentication.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import it.authentication.map.MaggiorenniMapper;
import it.authentication.map.MinorenniMapper;
import it.authentication.model.dto.MinorenniDTO;
import it.authentication.model.dto.NewPlayerDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.Maggiorenni;
import it.authentication.model.entity.Minorenni;
import it.authentication.model.entity.VerificationToken;
import it.authentication.repository.MaggiorenniRepository;
import it.authentication.repository.MinorenniRepository;
import it.authentication.repository.VerificationTokenRepository;

@Service
public class NewMaggiorenneService extends BaseService {
	@Autowired
	private MaggiorenniRepository mr;
	@Autowired
	private MinorenniRepository minr;
	@Autowired
	private VerificationTokenRepository vtr;
	@Autowired
	JavaMailSender javaMailSender;

	@Transactional(rollbackFor = Exception.class)
	public ResponseDTO newMagg(NewPlayerDTO newPlayerDTO) throws Exception {
		responseReset();
		Maggiorenni m = MaggiorenniMapper.MAPPER.mapToMaggiorenni(newPlayerDTO.getMaggiorenne());
		StringBuilder validMag = valid(m);
		Maggiorenni emailChecK = mr.findByEmail(m.getEmail());
		if (emailChecK != null) {
			response.setStatus(false);
			checkOrThrow("email gia esiste");
		}
		if (validMag.isEmpty()) {
			try {
				// salvo il maggiorenne
				Maggiorenni maggiorenni = mr.save(m);
				response.getContent().put("maggiorenne", maggiorenni);
				// salvo i minorenni
				int count = 0;
				for (MinorenniDTO minorenniDTO : newPlayerDTO.getMinorenni()) {
					minorenniDTO.setMaggiorenni(maggiorenni);
					Minorenni min = MinorenniMapper.MAPPER.mapToMinorenni(minorenniDTO);
					Minorenni minorenni = minr.save(min);
					response.getContent().put("minorenni" + count++, minorenni);

				}
				// salvo il token di conferma;
				VerificationToken tokenn = new VerificationToken();
				tokenn.setMaggiorenneId(maggiorenni.getIdMaggiorenne());
				vtr.save(tokenn);
				// creo il link per la verifica
				String link = "<b>Caro amico</b>,<br><i>conferma la tua email cliccando sul link.</i><br><i>Scarica l'allegato firmalo e portalo in segreteria. </i><br>"+"http://localhost:8080/maggiorenne/registrationConfirm?token=" + tokenn.getToken();
				// creo pdf da inviare
				ByteArrayInputStream file = pdf(maggiorenni, newPlayerDTO.getMinorenni());
				// invio email sahcska
				sendMailPdf(link, maggiorenni.getEmail(), file);

			} catch (Exception e) {
				response.setStatus(false);
				checkOrThrow(e.getMessage());
			}
		} else {
			response.setStatus(false);
			checkOrThrow(validMag.toString());
		}
		return response;

	}

	public ResponseDTO verification(String Token) {
		responseReset();
		VerificationToken v = vtr.findByToken(Token);
		if (v == null) {
			response.setStatus(false);
			response.setError("Il codice non e corretto");
		} else {
			// rendo attivo maggiorenne
			Optional<Maggiorenni> maggiorenne = mr.findById(v.getMaggiorenneId());
			maggiorenne.get().setActive((byte) 1);
			mr.save(maggiorenne.get());
			response.setMessage("attivazione avvenuta con successo");
		}
		return response;

	}

	public ResponseDTO sendMail(String message, String mailTo) throws Exception {
		try {
			final SimpleMailMessage email = new SimpleMailMessage();
			email.setTo(mailTo);
			email.setSubject("Conferma Email");
			email.setText("Clicca sul link per validare la tua email " + message);
			email.setFrom("vitocangiano@virgilio.it");
			
			javaMailSender.send(email);
		} catch (Exception e) {
			response.setStatus(false);
			checkOrThrow("mail non inviata" + e.getMessage());
		}

		return response;
	}

	public ResponseDTO sendMailPdf(String messa, String mailTo, ByteArrayInputStream pdf) throws Exception {
		try {

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setSubject("San domenico Sport Club");
			helper.setFrom("citocangiano@virgilio.it");
			helper.setTo(mailTo);

			helper.setText(messa, true);

			helper.addAttachment("Iscrizione.pdf", new ByteArrayResource(IOUtils.toByteArray(pdf)));

			javaMailSender.send(message);
		} catch (Exception e) {
			response.setStatus(false);
			checkOrThrow("mail non inviata" + e.getMessage());
		}

		return response;
	}

	public static ByteArrayInputStream pdf(Maggiorenni maggiorenni, List<MinorenniDTO> list) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			Date date = maggiorenni.getDataDiNascita();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String dataMagg = formatter.format(date);

			PdfWriter.getInstance(document, out);
			document.open();
			Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
			Font title = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);
			// Logo
			Path path = Paths.get(ClassLoader.getSystemResource("logoEsteso.png").toURI());
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			img.scaleAbsolute(500f, 100f);
			img.setAlignment(Element.ALIGN_CENTER);
			document.add(img);

			Paragraph titolo = new Paragraph("PROGETTO SOCIALE SPORTIVO SCUOLA DELLO SPORT", title);
			titolo.setAlignment(Element.ALIGN_CENTER);
			document.add(titolo);
			document.add(Chunk.NEWLINE);
			// genitore
			Paragraph genitore = new Paragraph("Io Stottoscritto " + maggiorenni.getNome() + " "
					+ maggiorenni.getCognome() + " nato a " + maggiorenni.getLuogoDiNascita() + " il " + dataMagg
					+ " residente in via " + maggiorenni.getIndirizzo() + " codice fiscale " + maggiorenni.getCf()
					+ " email " + maggiorenni.getEmail() + " telefono " + maggiorenni.getTelefono(), fontHeader);
			genitore.setAlignment(Element.ALIGN_LEFT);
			document.add(genitore);

			Paragraph testo = new Paragraph("Avente la podestà genitoriale di :", fontHeader);
			testo.setAlignment(Element.ALIGN_CENTER);
			document.add(testo);

			// minorenni
			for (MinorenniDTO m : list) {
				String dataMin = formatter.format(m.getDataDiNascita());
				Paragraph mino = new Paragraph(m.getNome() + " " + m.getCognome() + " nato a " + m.getLuogoDiNascita()
						+ " il " + dataMin + " codice fiscale " + m.getCf() + " indirizzo " + m.getIndirizzo(),
						fontHeader);
				mino.setAlignment(Element.ALIGN_LEFT);
				document.add(mino);
				document.add(Chunk.NEWLINE);
			}
			// dichiarazione
			Paragraph tes = new Paragraph("In ottemperanza con le leggi vigenti dichiaro :", fontHeader);
			tes.setAlignment(Element.ALIGN_CENTER);
			document.add(tes);
			document.add(Chunk.NEWLINE);
			// testo

			com.lowagie.text.List li = new com.lowagie.text.List();
			li.add("Di avere la podestà genitoriale sul/i minore/i sopra indicato/i");
			li.add("Che i/l minore/i gode di sana e robusta costituzione, periodicamente da me fatto/a controllare dal medico specialistico ed é idoneo/a a praticare attivita sportive/ricreative all'aperto.");
			li.add("Non è un soggetto allergico");
			li.add("Ho visionato la struttura e ne riconosco l'ottimo stato manutentivo, sono a conoscenza del contratto assicurativo e delle clausole assicurative ed i limiti assicurativi,per quanto esonero l'A.S.D. San Domenico Sport Club e /o qualsiasi altra socità sportiva o associazione sin d'ora da ogni responsabilità in merito ad eventuali danni personali subiti dal minore e per danni causati dal suo comportamento a persone e/o cose a causa del suo comportaminto.");
			li.add("Sono a conoscenza che l'A.S.D.San dDomenico Sport club pubblicherà a scopo pubblicitario foto/ video nei vari canali di divulgazione dell'evento dove potrebbero esserci foto/video del/i minore/i e ne do il consenso");
			li.add("Riconosco il versamento del contributo sociale attinente all'attività sportiva in oggetto.");
			li.add("Riconosco che il contributo sociale non puo essere restituito.");
			li.add("Consenso ai sensi di legge n°2016/769,recante disposizioni a tutela delle persone e degli altri soggetti e a tutela dei dati personali. i richiedenti forniscono il consenso al trattamento dei protri dati personali ,foto,video,gratuitamente per la ricezione di ulteriori proposte ed informazioni.i sottoscritti si riservano di comunicare al responsabile dati dell A.S.D.San Domenico Sport Club le modifiche, la cancellazione dei propri dati /foto/video personali.");
			li.add("Allego documento di riconoscimento.");
			document.add(li);
			// note
			Paragraph note = new Paragraph(
					"Documenti minore/i richiesti : 1) 2 foto Tessera 2) Certificato medico di sana e robusta costituzione ",
					fontHeader);
			note.setAlignment(Element.ALIGN_CENTER);
			document.add(note);
			document.add(Chunk.NEWLINE);
			// firma
			String dataIsc = formatter.format(maggiorenni.getDataCreazione());
			Paragraph data = new Paragraph(
					"Napoli, " + dataIsc
							+ "                                                                         Firma",
					fontHeader);
			data.setAlignment(Element.ALIGN_CENTER);
			document.add(data);
			document.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ByteArrayInputStream(out.toByteArray());

	}

}
