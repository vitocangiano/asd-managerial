package it.authentication.service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.authentication.jwt.JwtProvider;
import it.authentication.model.dto.JwtDTO;
import it.authentication.model.dto.ResponseDTO;
import it.authentication.model.dto.SignUpDTO;
import it.authentication.model.entity.TbUser;
import it.authentication.model.entity.TbUserRole;
import it.authentication.repository.TbUserRepository;
import it.authentication.repository.TbUserRoleRepository;

@Service
public class AuthService extends BaseService {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	TbUserSessionService uss;
	@Autowired
	TbUserRepository ur;
	@Autowired
	TbUserRoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	TbUserRoleJoinService urjs;

	public ResponseDTO authentication(String username, String password, String userAgent) {
		responseReset();
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generateJwtToken(authentication);
			response.getContent().put("jwt", new JwtDTO(jwt));
			if (authentication == null) {
				throw new Exception("Username o password errati");
			} else {
				// inserisco una riga di sessione
				UserMain customUser = (UserMain) authentication.getPrincipal();
				int userId = customUser.getUserId();
				response.getContent().put("user", ur.findById(userId));
				MessageDigest md = null;
				md = MessageDigest.getInstance("MD5");
				md.update(userAgent.getBytes());
				byte[] digest = md.digest();
				String md5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
				// TODO: controllare status sessione
				response.getContent().put("userSession",
						uss.save(userAgent, new Date(), md5, userId).getContent().get("userSession"));
			}
		} catch (Exception e) {
			setError(e);
		}
		return response;
	}

	public ResponseDTO signUp(SignUpDTO signUpRequest) {
		responseReset();
		StringBuilder error = new StringBuilder();
		
			StringBuilder e = valid(signUpRequest);
			if (!e.isEmpty()) {
				error.append(e.toString());
			}
			if (ur.existsByUsername(signUpRequest.getUsername())) {
				error.append("Username già presente");
			}
			if (ur.existsByEmail(signUpRequest.getEmail())) {
				error.append("L'email già presente");
			}
			// Creating user's account
			TbUser user = new TbUser((byte) 1, new Date(), null, signUpRequest.getEmail(),
					encoder.encode(signUpRequest.getPassword()), signUpRequest.getUsername());
			List<String> strRoles = signUpRequest.getRole();
			List<TbUserRole> roles = new ArrayList<TbUserRole>();
			// errori - ruoli non trovati
			strRoles.forEach(role -> {
				TbUserRole adminRole = roleRepository.findByName(role);
				if (adminRole != null) {
					roles.add(adminRole);
				} else {
					error.append(role + " non trovato" + "\r\n");
				}
			});
			if (error.isEmpty()) {
				// salvo e mando risposta user
				TbUser u = ur.save(user);
				response.getContent().put("user", u);
				for (TbUserRole ur : roles) {
					// salvo e mando risposta i ruoli
					response.getContent().put("role" + ur.getName(),
							urjs.save((byte) 1, new Date(), null, u.getUserId(), ur.getUserRoleId()));
				}
			} else {
				response.setStatus(false);
				response.setError(error.toString());
				return response;
			}
		
		
		return response;
	}
}
