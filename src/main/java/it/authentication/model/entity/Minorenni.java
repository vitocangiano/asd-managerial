package it.authentication.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the minorenni database table.
 * 
 */
@Entity
@NamedQuery(name="Minorenni.findAll", query="SELECT m FROM Minorenni m")
public class Minorenni implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_minorenne")
	private long idMinorenne;

	private String cf;

	private String cognome;

	@Temporal(TemporalType.DATE)
	@Column(name="data_di_nascita")
	private Date dataDiNascita;

	private String indirizzo;

	@Column(name="luogo_di_nascita")
	private String luogoDiNascita;
	@Column(name="minorenne_UUID")
	private UUID minorenneUUID;

	private String nome;

	//bi-directional many-to-one association to Maggiorenni
	@ManyToOne
	@JoinColumn(name="maggiorenne_id")
	private Maggiorenni maggiorenni;

	public Minorenni() {
		this.minorenneUUID=UUID.randomUUID();
	}

	public long getIdMinorenne() {
		return this.idMinorenne;
	}

	public void setIdMinorenne(long idMinorenne) {
		this.idMinorenne = idMinorenne;
	}

	public String getCf() {
		return this.cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return this.dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLuogoDiNascita() {
		return this.luogoDiNascita;
	}

	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public UUID getMinorenneUUID() {
		return this.minorenneUUID;
	}

	public void setMinorenneUUID(UUID minorenne_UUID) {
		this.minorenneUUID = minorenne_UUID;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Maggiorenni getMaggiorenni() {
		return this.maggiorenni;
	}

	public void setMaggiorenni(Maggiorenni maggiorenni) {
		this.maggiorenni = maggiorenni;
	}

}