package it.authentication.model.entity;

import java.io.Serializable;



import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the maggiorenni database table.
 * 
 */
@Entity
@NamedQuery(name="Maggiorenni.findAll", query="SELECT m FROM Maggiorenni m")
public class Maggiorenni implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_maggiorenne")
	private long idMaggiorenne;

	private byte active;

	private String cf;

	private String cognome;

	@Temporal(TemporalType.DATE)
	@Column(name="data_di_nascita")
	private Date dataDiNascita;
	@Email
	private String email;

	private String indirizzo;

	@Column(name="luogo_di_nascita")
	private String luogoDiNascita;

	private UUID maggiorenniUUID;

	private String nome;

	private String telefono;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_creazione")
	private Date dataCreazione;

	//bi-directional many-to-one association to Minorenni
	@JsonIgnore
	@OneToMany(mappedBy="maggiorenni")
	private List<Minorenni> minorennis;


	public Maggiorenni() {
		this.maggiorenniUUID= UUID.randomUUID();
		this.active=0;
		this.dataCreazione= new Date();
	}

	public long getIdMaggiorenne() {
		return this.idMaggiorenne;
	}

	public void setIdMaggiorenne(long idMaggiorenne) {
		this.idMaggiorenne = idMaggiorenne;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public UUID getMaggiorenniUUID() {
		return this.maggiorenniUUID;
	}

	public void setMaggiorenniUUID(UUID maggiorenniUUID) {
		this.maggiorenniUUID = maggiorenniUUID;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Minorenni> getMinorennis() {
		return this.minorennis;
	}

	public void setMinorennis(List<Minorenni> minorennis) {
		this.minorennis = minorennis;
	}

	public Minorenni addMinorenni(Minorenni minorenni) {
		getMinorennis().add(minorenni);
		minorenni.setMaggiorenni(this);

		return minorenni;
	}

	public Minorenni removeMinorenni(Minorenni minorenni) {
		getMinorennis().remove(minorenni);
		minorenni.setMaggiorenni(null);

		return minorenni;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	

}