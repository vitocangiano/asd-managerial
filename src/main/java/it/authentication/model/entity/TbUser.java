package it.authentication.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the tb_user database table.
 * 
 */
@Entity
@Table(name = "tb_user")
@NamedQuery(name = "TbUser.findAll", query = "SELECT t FROM TbUser t")
public class TbUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	private byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTo;

	private String email;

	@JsonIgnore
	private String password;

	private String username;

	private UUID userUUID;

	private String frontEndConfigurationJson;

	@JsonIgnore
	@OneToMany(mappedBy = "tbUser")
	private List<TbUserRoleJoin> tbUserRoleJoins;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_user_role_join", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "userRoleId"))
	private Set<TbUserRole> roles = new HashSet<>();

	public TbUser() {
		this(null, null, null, null, null, null);
	}

	public TbUser(Byte active, Date dateFrom, Date dateTo, String email, String password, String username) {
		super();
		if (active != null) {
			this.active = active;
		}
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.email = email;
		this.password = password;
		this.username = username;
		userUUID = UUID.randomUUID();
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UUID getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(UUID userUUID) {
		this.userUUID = userUUID;
	}

	public List<TbUserRoleJoin> getTbUserRoleJoins() {
		return this.tbUserRoleJoins;
	}

	public void setTbUserRoleJoins(List<TbUserRoleJoin> tbUserRoleJoins) {
		this.tbUserRoleJoins = tbUserRoleJoins;
	}

	public TbUserRoleJoin addTbUserRoleJoin(TbUserRoleJoin tbUserRoleJoin) {
		getTbUserRoleJoins().add(tbUserRoleJoin);
		tbUserRoleJoin.setTbUser(this);

		return tbUserRoleJoin;
	}

	public TbUserRoleJoin removeTbUserRoleJoin(TbUserRoleJoin tbUserRoleJoin) {
		getTbUserRoleJoins().remove(tbUserRoleJoin);
		tbUserRoleJoin.setTbUser(null);

		return tbUserRoleJoin;
	}

	public String getFrontEndConfigurationJson() {
		return frontEndConfigurationJson;
	}

	public void setFrontEndConfigurationJson(String frontEndConfigurationJson) {
		this.frontEndConfigurationJson = frontEndConfigurationJson;
	}

	public Set<TbUserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<TbUserRole> roles) {
		this.roles = roles;
	}

}