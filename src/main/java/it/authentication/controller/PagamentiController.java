package it.authentication.controller;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.authentication.model.dto.PagamentiDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.service.BaseService;
import it.authentication.service.PagamentiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
@CrossOrigin
@RestController
@RequestMapping("/api/pagamenti")
public class PagamentiController extends BaseService{
	@Autowired
	private PagamentiService ps;

	
	@PostMapping("/add")
	public ResponseDTO add(@RequestBody PagamentiDTO pagamentiDTO) {
		return ps.save(pagamentiDTO);
		
	}
	@GetMapping("/get")
	public ResponseDTO get(@RequestParam("UUID") UUID UUID) {
		return ps.get(UUID);
		
	}
	
	@PostMapping("/errato")
	public ResponseDTO delete(@RequestParam("UUID") UUID UUID) {
		return ps.errato(UUID);
		
	}
	@GetMapping("/export")
	public ResponseEntity<Resource> delete() {
		 InputStreamResource file = new InputStreamResource(ps.pagamentiExport());
	
	return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "pagamenti")
	        .contentType(MediaType.parseMediaType("application/csv"))
	        .body(file);
	}
	
	

}
