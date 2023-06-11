package it.authentication.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.TbUserRoleJoin;
import it.authentication.repository.TbUserRoleJoinRepository;

@Service
public class TbUserRoleJoinService extends BaseService {
	@Autowired
	TbUserRoleJoinRepository urjr;

	public ResponseDTO save(byte active, Date dateFrom, Date dateTo, int userId, byte userRoleId) {
		try {
			TbUserRoleJoin urj = new TbUserRoleJoin();
			urj.setActive(active);
			urj.setDateFrom(dateFrom);
			urj.setDateTo(dateTo);
			urj.setDateTo(dateTo);
			urj.setUserId(userId);
			urj.setUserRoleId(userRoleId);
			urjr.save(urj);
			response.getContent().put("userRoleJoin", urj);
		} catch (Exception e) {
			setError(e);
		}
		return response;
	}
}
