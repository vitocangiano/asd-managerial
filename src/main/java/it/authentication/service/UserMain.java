package it.authentication.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.authentication.model.entity.TbUser;

public class UserMain implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private UUID userUUID;
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserMain(Integer userId, UUID userUUID, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.userUUID = userUUID;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserMain build(TbUser user) {
		// prende il ruolo(necessario ROLE_NOMEAUTORIZZAZIONE);
		// SimpleGrantedAuthority CLASSE ha un costruttore che prende come parametro il
		// nome del ruolo collegato all autority
		List<GrantedAuthority> authorities = user.getTbUserRoleJoins().stream()
				.map(role -> new SimpleGrantedAuthority(role.getTbUserRole().getName())).collect(Collectors.toList());
		return new UserMain(user.getUserId(), user.getUserUUID(), user.getUsername(), user.getEmail(),
				user.getPassword(), authorities);
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserMain user = (UserMain) o;
		return Objects.equals(userId, user.userId);
	}

	public Integer getUserId() {
		return userId;
	}

	public UUID getUserUUID() {
		return userUUID;
	}
}