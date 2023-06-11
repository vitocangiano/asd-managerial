package it.authentication.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.authentication.map.MinorenniMapper;
import it.authentication.model.dto.MinorenniDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.Minorenni;
import it.authentication.repository.MinorenniRepository;


@Service
public class MinorenniService extends BaseService{
	@Autowired
	MinorenniRepository mr;
	
	public ResponseDTO save (MinorenniDTO minorenniDTO) {
		try {
			Minorenni m = MinorenniMapper.MAPPER.mapToMinorenni(minorenniDTO);
			Minorenni minorenni = mr.save(m);
			response.getContent().put("minorenni", minorenni);
		} catch (Exception e) {
			response.setError(e.getMessage());
			response.setStatus(false);
		}
		return response;
		
	}
	
	public ResponseDTO upDate (MinorenniDTO minorenniDTO) {
		Minorenni ma= mr.findByMinorenneUUID(minorenniDTO.getMinorenneUUID());
		if(ma!=null) {
			try {
				Minorenni m = MinorenniMapper.MAPPER.mapToMinorenni(minorenniDTO);
				m.setIdMinorenne(ma.getIdMinorenne());
				m.setMaggiorenni(ma.getMaggiorenni());
				mr.save(m);
			} catch (Exception e) {
				response.setError(e.getMessage());
				response.setStatus(false);
			}
			
		}
		return response;
		
	}
	

}
