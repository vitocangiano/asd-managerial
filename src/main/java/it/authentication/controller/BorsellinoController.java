package it.authentication.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.authentication.model.dto.BorsellinoDTO;
import it.authentication.model.dto.NewPlayerDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.service.BaseService;
import it.authentication.service.BorsellinoService;

@CrossOrigin
@RestController
@RequestMapping("/api/borsellino")
public class BorsellinoController extends BaseService{
	@Autowired
	BorsellinoService bs;
	
	@PostMapping("/add")
	public ResponseDTO add(@RequestBody BorsellinoDTO borsellinoDTO) {
		try {
			bs.transazione(borsellinoDTO);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
		
	}
	@GetMapping("/get")
	public ResponseDTO get(@RequestParam("UUID") UUID UUID) {
		return bs.get(UUID);
		
	}
	@DeleteMapping("/delete")
	public ResponseDTO delete(@RequestParam("UUID") UUID UUID) {
		return bs.delete(UUID);
		
	}
	@GetMapping("/export")
	public ResponseEntity<Resource> delete() {
		 InputStreamResource file = new InputStreamResource(bs.pagamentiExport());
	
	return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "borsellino")
	        .contentType(MediaType.parseMediaType("application/csv"))
	        .body(file);
	}

}
