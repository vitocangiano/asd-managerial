package it.authentication.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the tb_user_session database table.
 * 
 */
@Entity
@Table(name="tb_user_session")
@NamedQuery(name="TbUserSession.findAll", query="SELECT t FROM TbUserSession t")
public class TbUserSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userSessionId;

	@JsonIgnore
	@Lob
	private String agent;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFrom;

	@JsonIgnore
	private String md5;

	@JsonIgnore
	private int userId;

	private UUID userSessionUUID;

	public TbUserSession() {
		userSessionUUID = UUID.randomUUID();
	}

	public int getUserSessionId() {
		return this.userSessionId;
	}

	public void setUserSessionId(int userSessionId) {
		this.userSessionId = userSessionId;
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

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UUID getUserSessionUUID() {
		return userSessionUUID;
	}

	public void setUserSessionUUID(UUID userSessionUUID) {
		this.userSessionUUID = userSessionUUID;
	}
}