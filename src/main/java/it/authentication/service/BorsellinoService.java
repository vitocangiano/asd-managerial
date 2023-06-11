package it.authentication.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.authentication.map.BorsellinoMapper;
import it.authentication.model.dto.BorsellinoDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.Borsellino;
import it.authentication.model.entity.Maggiorenni;
import it.authentication.model.entity.Minorenni;
import it.authentication.model.entity.Pagamenti;
import it.authentication.repository.BorsellinoRepository;
import it.authentication.repository.MinorenniRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BorsellinoService extends BaseService{
	@Autowired
	BorsellinoRepository br;
	@Autowired
	MinorenniRepository mr;
	@Transactional(rollbackFor = Exception.class) 
	public ResponseDTO transazione(BorsellinoDTO borsellinoDTO) {
		try {
			Borsellino borsellino = BorsellinoMapper.MAPPER.mapToBorsellino(borsellinoDTO);
			Minorenni minorenne = mr.findByMinorenneUUID(borsellinoDTO.getMinorenneUUID());
			borsellino.setIdMinorenni(minorenne.getIdMinorenne());
			br.save(borsellino);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}
	@Transactional(rollbackFor = Exception.class) 
	public ResponseDTO get(UUID minorenneUUID) {
		try {	
			Minorenni minorenne = mr.findByMinorenneUUID(minorenneUUID);
			List<Borsellino> listB= br.findByIdMinorenni(minorenne.getIdMinorenne());
			response.getContent().put("borsellino", listB);
			response.getContent().put("minorenne", minorenne);
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
		}
		return response;
	}
	public ResponseDTO delete(UUID borsellinoUUID) {
		try {
			Borsellino borsellino = br.findByBorsellinoUUID(borsellinoUUID);
			br.delete(borsellino);
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
		}
		return response;
	}
	public ByteArrayInputStream pagamentiExport() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<Borsellino>listBorsellino= br.findAll();
		if(listBorsellino!=null) {
			try {
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);
				String[] intestazione =new String[] {"idTransazione","cognome","nome","cf","data","note","importo"};
				csvPrinter.printRecord(intestazione);
				for(Borsellino b : listBorsellino) {
					Optional<Minorenni> m = mr.findById(b.getIdMinorenni());
					List<String> data = Arrays.asList(	
							String.valueOf(b.getIdBorsellino()),
							m.get().getCognome(),
							m.get().getNome(),
							m.get().getCf(),
							String.valueOf(b.getData()),
							b.getNote(),
							String.valueOf(b.getImporto())
							
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
