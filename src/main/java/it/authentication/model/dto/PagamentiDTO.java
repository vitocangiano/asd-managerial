package it.authentication.model.dto;

import java.util.Date;
import java.util.UUID;

public class PagamentiDTO {
	private Date data;

	private java.math.BigInteger idMaggiorenne;

	private float importo;

	private String numeroPagamento;
	
	private String note;

	private UUID pagamentiUUID;
	
	private UUID maggiorenneUUID;

	public PagamentiDTO() {
		this.data=new Date();
		this.pagamentiUUID=UUID.randomUUID();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public java.math.BigInteger getIdMaggiorenne() {
		return idMaggiorenne;
	}

	public void setIdMaggiorenne(java.math.BigInteger idMaggiorenne) {
		this.idMaggiorenne = idMaggiorenne;
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public String getNumeroPagamento() {
		return numeroPagamento;
	}

	public void setNumeroPagamento(String numeroPagamento) {
		this.numeroPagamento = numeroPagamento;
	}

	public UUID getPagamentiUUID() {
		return pagamentiUUID;
	}

	public void setPagamentiUUID(UUID pagamentiUUID) {
		this.pagamentiUUID = pagamentiUUID;
	}

	public UUID getMaggiorenneUUID() {
		return maggiorenneUUID;
	}

	public void setMaggiorenneUUID(UUID maggiorenneUUID) {
		this.maggiorenneUUID = maggiorenneUUID;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

}
