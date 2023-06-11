package it.authentication.model.dto;

import java.util.Date;
import java.util.UUID;

import it.authentication.model.entity.Maggiorenni;



public class MinorenniDTO {
	
	private long idMinorenne;

	private String cf;

	private String cognome;

	private Date dataDiNascita;

	private String indirizzo;

	private String luogoDiNascita;

	private UUID minorenneUUID;

	private String nome;
	
	private Maggiorenni maggiorenni;
	
	public MinorenniDTO(){
		this.minorenneUUID=UUID.randomUUID();
	}

	public long getIdMinorenne() {
		return idMinorenne;
	}

	public void setIdMinorenne(long idMinorenne) {
		this.idMinorenne = idMinorenne;
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

	public UUID getMinorenneUUID() {
		return minorenneUUID;
	}

	public void setMinorenneUUID(UUID minorenne_UUID) {
		this.minorenneUUID = minorenne_UUID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Maggiorenni getMaggiorenni() {
		return maggiorenni;
	}

	public void setMaggiorenni(Maggiorenni maggiorenni) {
		this.maggiorenni = maggiorenni;
	}
	
	
	

}
