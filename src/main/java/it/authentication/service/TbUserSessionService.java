package it.authentication.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.TbUserSession;
import it.authentication.repository.TbUserSessionRepository;

@Service
public class TbUserSessionService extends BaseService {
	@Autowired
	TbUserSessionRepository usr;

	public ResponseDTO save(String agent, Date dateFrom, String md5, int userId) {
		try {
			TbUserSession us = new TbUserSession();
			us.setAgent(agent);
			us.setDateFrom(dateFrom);
			us.setMd5(md5);
			us.setUserId(userId);
			usr.save(us);
			response.getContent().put("userSession", us);
		} catch (Exception e) {
			setError(e);
		}
		return response;
	}
}
