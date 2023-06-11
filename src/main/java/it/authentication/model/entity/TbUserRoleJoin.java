package it.authentication.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.UUID;

/**
 * The persistent class for the tb_user_role_join database table.
 * 
 */
@Entity
@Table(name = "tb_user_role_join")
@NamedQuery(name = "TbUserRoleJoin.findAll", query = "SELECT t FROM TbUserRoleJoin t")
public class TbUserRoleJoin implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userRoleJoinId;

	private byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTo;

	@JsonIgnore
	@Column(nullable = false)
	private int userId;

	@JsonIgnore
	@Column(nullable = false)
	private byte userRoleId;

	private UUID userRoleJoinUUID;

	@JsonIgnore
	// bi-directional many-to-one association to TbUser
	@ManyToOne(optional = false)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private TbUser tbUser;

	@JsonIgnore
	// bi-directional many-to-one association to TbUserRole
	@ManyToOne
	@JoinColumn(name = "userRoleId", insertable = false, updatable = false)
	private TbUserRole tbUserRole;

	public TbUserRoleJoin() {
		userRoleJoinUUID = UUID.randomUUID();
	}

	public int getUserRoleJoinId() {
		return this.userRoleJoinId;
	}

	public void setUserRoleJoinId(int userRoleJoinId) {
		this.userRoleJoinId = userRoleJoinId;
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

	public byte getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(byte userRoleId) {
		this.userRoleId = userRoleId;
	}

	public UUID getUserRoleJoinUUID() {
		return userRoleJoinUUID;
	}

	public void setUserRoleJoinUUID(UUID userRoleJoinUUID) {
		this.userRoleJoinUUID = userRoleJoinUUID;
	}

	public TbUser getTbUser() {
		return this.tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}

	public TbUserRole getTbUserRole() {
		return this.tbUserRole;
	}

	public void setTbUserRole(TbUserRole tbUserRole) {
		this.tbUserRole = tbUserRole;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}