package it.authentication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.authentication.model.dto.MaggiorenniDTO;
import it.authentication.model.dto.MinorenniDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.Minorenni;
import it.authentication.repository.MinorenniRepository;
import it.authentication.service.BaseService;
import it.authentication.service.MinorenniService;

@CrossOrigin
@RestController
@RequestMapping("/api/minorenne")
public class MinorenniController extends BaseService{
	@Autowired
	MinorenniRepository mr;
	@Autowired
	MinorenniService ms;
	@GetMapping("/getAll")
	public ResponseDTO all() {
		List<Minorenni> ma= mr.findAll();
		response.getContent().put("minorenni",ma);
		return response; 
	}
	@GetMapping("/findByCognome")
	public ResponseDTO cognome() {
		List<Minorenni> ma= mr.findByCognome("panico");
		response.getContent().put("minorenni",ma);
		return response; 
	}
	@DeleteMapping("/uuid")
	public ResponseDTO uuid(@RequestParam("UUID") java.util.UUID UUID) {
		Minorenni ma= mr.findByMinorenneUUID(UUID);
		try {
			mr.delete(ma);
		} catch (Exception e) {
			response.setError(e.getMessage());
		}
		response.getContent().put("minorenni",ma);
		return response; 
	}
	@PutMapping("/upDate")
	public ResponseDTO upLoad(@RequestBody MinorenniDTO minorenneDTO) {
	
		return ms.upDate(minorenneDTO);
	}

}
