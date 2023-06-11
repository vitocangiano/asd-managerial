package it.authentication.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import it.authentication.map.PagamentiMapper;
import it.authentication.model.dto.PagamentiDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.Borsellino;
import it.authentication.model.entity.Maggiorenni;
import it.authentication.model.entity.Minorenni;
import it.authentication.model.entity.Pagamenti;
import it.authentication.repository.MaggiorenniRepository;
import it.authentication.repository.Pagamentirepository;

@Service
public class PagamentiService extends BaseService {
	@Autowired
	Pagamentirepository pr;
	@Autowired
	MaggiorenniRepository mr;
	@Autowired
	NewMaggiorenneService nms;

	@Transactional(rollbackFor = Exception.class)
	public ResponseDTO save(PagamentiDTO pagamentiDTO) {
		try {
			Pagamenti pagamenti = PagamentiMapper.Mapper.mapToPagamenti(pagamentiDTO);
			Maggiorenni maggiorenne = mr.findByMaggiorenniUUID(pagamentiDTO.getMaggiorenneUUID());
			pagamenti.setIdMaggiorenne(maggiorenne.getIdMaggiorenne());
			Integer ultimaRicevuta = pr.ultimaRicevuta();
			if (ultimaRicevuta == null) {
				ultimaRicevuta = 0;
			}
			pagamenti.setNumeroPagamento(ultimaRicevuta + 1);
			pr.save(pagamenti);
			String message = "\"<b>Caro amico</b>,<br><i>In allegato ricevuta di pagamento.</i><br>\"+";
			ByteArrayInputStream file = pdf(maggiorenne, pagamenti);
			nms.sendMailPdf(message, maggiorenne.getEmail(), file);
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
		}
		return response;

	}

	public ResponseDTO get(UUID maggiorenneUUID) {
		try {

			Maggiorenni maggiorenne = mr.findByMaggiorenniUUID(maggiorenneUUID);
			List<Pagamenti> listB = pr.findByIdMaggiorenne(maggiorenne.getIdMaggiorenne());
			response.getContent().put("pagamenti", listB);
			response.getContent().put("maggiorenne", maggiorenne);
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
		}
		return response;
	}

	public ResponseDTO errato(UUID UUID) {
		try {
			Pagamenti pagamento = pr.findByPagamentiUUID(UUID);
			pagamento.setNote("-->ERRATO<--");
			pagamento.setImporto(0);
			pr.save(pagamento);
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
		}
		return response;
	}

	public static ByteArrayInputStream pdf(Maggiorenni maggiorenne, Pagamenti pagamento) {
		Document document = new Document(PageSize.A6.rotate());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			PdfWriter.getInstance(document, out);
			document.open();

			Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);
			Font fontFooter = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8);
			Font title = FontFactory.getFont(FontFactory.TIMES_BOLD, 12);

			Path path = Paths.get(ClassLoader.getSystemResource("logoEsteso.png").toURI());
			Image img = Image.getInstance(path.toAbsolutePath().toString());
			img.scaleAbsolute(150f, 45f);
			img.setAlignment(Element.ALIGN_CENTER);
			document.add(img);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String dataPag = formatter.format(pagamento.getData());
			Paragraph titolo = new Paragraph(
					"Ricevuta N° : " + pagamento.getNumeroPagamento() + "                     Data : " + dataPag, fontHeader);
			titolo.setAlignment(Element.ALIGN_LEFT);
			document.add(titolo);
			document.add(Chunk.NEWLINE);
			Paragraph nome = new Paragraph(
					"Ricevuti da : " + maggiorenne.getCognome().toUpperCase() +" "+ maggiorenne.getNome().toUpperCase(), fontHeader);
			titolo.setAlignment(Element.ALIGN_LEFT);
			document.add(nome);
			Paragraph code = new Paragraph(
					"Codice Fiscale : " + maggiorenne.getCf(), fontHeader);
			code.setAlignment(Element.ALIGN_LEFT);
			document.add(code);
			Paragraph note = new Paragraph(
					"Note : " + pagamento.getNote() + "  Importo : €" + pagamento.getImporto(), fontHeader);
			titolo.setAlignment(Element.ALIGN_LEFT);
			document.add(note);
			Paragraph per = new Paragraph(
					"Per : Corso Scuola Dello Sport", fontHeader);
			titolo.setAlignment(Element.ALIGN_LEFT);
			document.add(per);
			document.add(Chunk.NEWLINE);
			Paragraph timbro = new Paragraph(
					"A.S.D. SAN DOMENICO SPORT CLUB - Via San Domenico, 146 - 80126 (NA)", fontFooter);
			timbro.setAlignment(Element.ALIGN_CENTER);
			document.add(timbro);
			Paragraph cf = new Paragraph(
					"C.F: 95006570634 - P.IVA: 07833320638", fontFooter);
			cf.setAlignment(Element.ALIGN_CENTER);
			document.add(cf);
			
			document.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ByteArrayInputStream(out.toByteArray());

	}
	public ByteArrayInputStream pagamentiExport() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<Pagamenti>listPagamenti= pr.findAll();
		if(listPagamenti!=null) {
			try {
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);
				String[] intestazione =new String[] {"idTransazione","cognome","nome","cf","N° ricevuta","data","note","importo"};
				csvPrinter.printRecord(intestazione);
				for(Pagamenti p : listPagamenti) {
					Optional<Maggiorenni> m = mr.findById(p.getIdMaggiorenne());
					List<String> data = Arrays.asList(	
							String.valueOf(p.getIdPagamenti()),
							m.get().getCognome(),
							m.get().getNome(),
							m.get().getCf(),
							String.valueOf(p.getNumeroPagamento()),
							String.valueOf(p.getData()),
							p.getNote(),
							String.valueOf(p.getImporto())
							
							);
					csvPrinter.printRecord(data);
				}
				csvPrinter.flush();
				new ByteArrayInputStream(out.toByteArray());
			} catch (Exception e) {
				
			}
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
	

}
