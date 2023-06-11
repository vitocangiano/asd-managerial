package it.authentication.model.dto;

import java.util.Date;
import java.util.UUID;



public class MaggiorenniDTO {
	private long idMaggiorenne;

	private byte active;

	private String cf;

	private String cognome;

	private Date dataDiNascita;

	private String email;

	private String indirizzo;

	private String luogoDiNascita;

	private UUID maggiorenniUUID;

	private String nome;

	private String telefono;
	
	public MaggiorenniDTO() {
		this.maggiorenniUUID= UUID.randomUUID();
		this.active=0;
	}

	public long getIdMaggiorenne() {
		return idMaggiorenne;
	}

	public void setIdMaggiorenne(long idMaggiorenne) {
		this.idMaggiorenne = idMaggiorenne;
	}

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public UUID getMaggiorenniUUID() {
		return maggiorenniUUID;
	}

	public void setMaggiorenniUUID(UUID maggiorenniUUID) {
		this.maggiorenniUUID = maggiorenniUUID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
}
