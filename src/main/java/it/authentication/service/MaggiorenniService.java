package it.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.authentication.map.MaggiorenniMapper;
import it.authentication.model.dto.MaggiorenniDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.Maggiorenni;
import it.authentication.repository.MaggiorenniRepository;




@Service
public class MaggiorenniService extends BaseService{
	@Autowired
	private MaggiorenniRepository mr;
	
	public ResponseDTO savee(MaggiorenniDTO maggiorenniDTO) {
		try {
			Maggiorenni m = MaggiorenniMapper.MAPPER.mapToMaggiorenni(maggiorenniDTO);
			Maggiorenni maggiorenni = mr.save(m);
			response.getContent().put("maggiorenni", maggiorenni);
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
		}
		return response;
	}
	public ResponseDTO upLoad(MaggiorenniDTO maggiorenniDTO) {
		Maggiorenni maggiorenni=mr.findByMaggiorenniUUID(maggiorenniDTO.getMaggiorenniUUID());
		if (maggiorenni!=null) {
		try {
			Maggiorenni m = MaggiorenniMapper.MAPPER.mapToMaggiorenni(maggiorenniDTO);
			m.setIdMaggiorenne(maggiorenni.getIdMaggiorenne());
			mr.save(m);
			response.getContent().put("maggiorenni", maggiorenni);
		} catch (Exception e) {
			response.setStatus(false);
			response.setError(e.getMessage());
		}
		}else {
			response.setMessage("Maggiorenne non trovato");
		}
		return response;
	}
	

}
