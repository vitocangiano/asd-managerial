package it.authentication.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the vw_user_session database table.
 * 
 */
@Entity
@Immutable
@Table(name="vw_user_session")
@NamedQuery(name="VwUserSession.findAll", query="SELECT v FROM VwUserSession v")
public class VwUserSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	private String agent;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFrom;

	private String md5;

	private byte userActive;

	@JsonIgnore
	private int userId;

	private String username;

	@JsonIgnore
	@Id
	private int userSessionId;

	private UUID userSessionUUID;

	public VwUserSession() {
	}

	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public Date getDateFrom() {
		return this.dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public byte getUserActive() {
		return this.userActive;
	}

	public void setUserActive(byte userActive) {
		this.userActive = userActive;
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

	public int getUserSessionId() {
		return this.userSessionId;
	}

	public void setUserSessionId(int userSessionId) {
		this.userSessionId = userSessionId;
	}

	public UUID getUserSessionUUID() {
		return userSessionUUID;
	}

	public void setUserSessionUUID(UUID userSessionUUID) {
		this.userSessionUUID = userSessionUUID;
	}

}