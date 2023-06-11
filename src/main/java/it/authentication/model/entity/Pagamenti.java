package it.authentication.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the pagamenti database table.
 * 
 */
@Entity
@NamedQuery(name="Pagamenti.findAll", query="SELECT p FROM Pagamenti p")
public class Pagamenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pagamenti")
	private long idPagamenti;

	@Temporal(TemporalType.DATE)
	private Date data;
	@Column(name="id_maggiorenne")
	private long idMaggiorenne;

	private float importo;

	private Integer numeroPagamento;
	
	private String note;
	@Column(name="pagamenti_UUID")
	private UUID pagamentiUUID;

	public Pagamenti() {
		this.data=new Date();
	}

	public long getIdPagamenti() {
		return this.idPagamenti;
	}

	public void setIdPagamenti(long idPagamenti) {
		this.idPagamenti = idPagamenti;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getIdMaggiorenne() {
		return this.idMaggiorenne;
	}

	public void setIdMaggiorenne(long idMaggiorenne) {
		this.idMaggiorenne = idMaggiorenne;
	}

	public float getImporto() {
		return this.importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public Integer getNumeroPagamento() {
		return this.numeroPagamento;
	}

	public void setNumeroPagamento(Integer numeroPagamento) {
		this.numeroPagamento = numeroPagamento;
	}

	public UUID getPagamentiUUID() {
		return this.pagamentiUUID;
	}

	public void setPagamentiUUID(UUID pagamentiUUID) {
		this.pagamentiUUID = pagamentiUUID;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

}