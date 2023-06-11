package it.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.authentication.model.dto.ResponseDTO;
import it.authentication.repository.TbUserRoleRepository;
import it.authentication.repository.VwUserRoleRepository;

@Service
public class DefaultService extends BaseService {
	
	@Autowired
	TbUserRoleRepository urr;
	@Autowired
	VwUserRoleRepository vurr;
	
	public ResponseDTO index() {
		
		getUserRole();
		getUserData();
		return response;
	}
	
	
	public ResponseDTO getUserRole() {
		try {
			response.getContent().put("userRole", urr.findAll());
		} catch (Exception e) {
			setError(e);
		}
		return response;
	}
	
	public ResponseDTO getUserData() {
		try {
			response.getContent().put("userData", vurr.findAll());
		} catch (Exception e) {
			setError(e);
		}
		return response;
	}

}
