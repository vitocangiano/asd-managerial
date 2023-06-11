package it.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.entity.TbUser;
import it.authentication.repository.TbUserRepository;

@Service
public class TbUserService implements UserDetailsService {

	@Autowired
	TbUserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TbUser user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		return UserMain.build(user);
	}

	public ResponseDTO setConfig(String username, String frontEndConfigurationJson) {
		ResponseDTO response = new ResponseDTO();
		try {
			TbUser user = userRepository.findByUsername(username).orElseThrow(
					() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
			user.setFrontEndConfigurationJson(frontEndConfigurationJson);
			userRepository.save(user);
			response.getContent().put("User", user);
		} catch (Exception e) {
			response.setError(e.getMessage());
		}
		return response;
	}
}