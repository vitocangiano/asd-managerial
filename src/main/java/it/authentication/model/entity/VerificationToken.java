package it.authentication.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

import javax.print.attribute.standard.DateTimeAtCompleted;

import java.math.BigInteger;


/**
 * The persistent class for the verification_token database table.
 * 
 */
@Entity
@Table(name="verification_token")
@NamedQuery(name="VerificationToken.findAll", query="SELECT v FROM VerificationToken v")
public class VerificationToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	@Id
	@Column(name="id_token")
	private long idToken;

	@Column(name="maggiorenne_id")
	private long maggiorenneId;

	private String token;

	public VerificationToken() {
		this.data=new Date();
		this.token=UUID.randomUUID().toString();
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getIdToken() {
		return this.idToken;
	}

	public void setIdToken(long idToken) {
		this.idToken = idToken;
	}

	public long getMaggiorenneId() {
		return this.maggiorenneId;
	}

	public void setMaggiorenneId(long maggiorenneId) {
		this.maggiorenneId = maggiorenneId;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}