package it.authentication.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the vw_user_role database table.
 * 
 */
@Entity
@Immutable
@Table(name="vw_user_role")
@NamedQuery(name="VwUserRole.findAll", query="SELECT v FROM VwUserRole v")
public class VwUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;

	private String password;

	private byte roleActive;

	private String roleName;

	private byte userActive;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userDateTo;
	
	@JsonIgnore
	@Id
	private int userId;

	private String username;

	private UUID userUUID;

	private byte userRoleActive;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userRoleDateFrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userRoleDateTo;

	@JsonIgnore
	private byte userRoleId;

	@JsonIgnore
	private int userRoleJoinId;

	private UUID userRoleJoinUUID;

	private UUID userRoleUUID;

	public VwUserRole() {
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

	public byte getRoleActive() {
		return this.roleActive;
	}

	public void setRoleActive(byte roleActive) {
		this.roleActive = roleActive;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public byte getUserActive() {
		return this.userActive;
	}

	public void setUserActive(byte userActive) {
		this.userActive = userActive;
	}

	public Date getUserDateFrom() {
		return this.userDateFrom;
	}

	public void setUserDateFrom(Date userDateFrom) {
		this.userDateFrom = userDateFrom;
	}

	public Date getUserDateTo() {
		return this.userDateTo;
	}

	public void setUserDateTo(Date userDateTo) {
		this.userDateTo = userDateTo;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte getUserRoleActive() {
		return this.userRoleActive;
	}

	public void setUserRoleActive(byte userRoleActive) {
		this.userRoleActive = userRoleActive;
	}

	public Date getUserRoleDateFrom() {
		return this.userRoleDateFrom;
	}

	public void setUserRoleDateFrom(Date userRoleDateFrom) {
		this.userRoleDateFrom = userRoleDateFrom;
	}

	public Date getUserRoleDateTo() {
		return this.userRoleDateTo;
	}

	public void setUserRoleDateTo(Date userRoleDateTo) {
		this.userRoleDateTo = userRoleDateTo;
	}

	public byte getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(byte userRoleId) {
		this.userRoleId = userRoleId;
	}

	public int getUserRoleJoinId() {
		return this.userRoleJoinId;
	}

	public void setUserRoleJoinId(int userRoleJoinId) {
		this.userRoleJoinId = userRoleJoinId;
	}

	public UUID getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(UUID userUUID) {
		this.userUUID = userUUID;
	}

	public UUID getUserRoleJoinUUID() {
		return userRoleJoinUUID;
	}

	public void setUserRoleJoinUUID(UUID userRoleJoinUUID) {
		this.userRoleJoinUUID = userRoleJoinUUID;
	}

	public UUID getUserRoleUUID() {
		return userRoleUUID;
	}

	public void setUserRoleUUID(UUID userRoleUUID) {
		this.userRoleUUID = userRoleUUID;
	}
}