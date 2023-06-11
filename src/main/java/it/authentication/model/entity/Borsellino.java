package it.authentication.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the borsellino database table.
 * 
 */
@Entity
@NamedQuery(name="Borsellino.findAll", query="SELECT b FROM Borsellino b")
public class Borsellino implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_borsellino")
	private long idBorsellino;

	private UUID borsellinoUUID;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(name="id_minorenni")
	private Long idMinorenni;

	private float importo;

	private String note;

	public Borsellino() {
		this.borsellinoUUID=UUID.randomUUID();
		this.data= new Date();
	}

	public long getIdBorsellino() {
		return this.idBorsellino;
	}

	public void setIdBorsellino(long idBorsellino) {
		this.idBorsellino = idBorsellino;
	}

	public UUID getBorsellinoUUID() {
		return this.borsellinoUUID;
	}

	public void setBorsellinoUUID(UUID borsellinoUUID) {
		this.borsellinoUUID = borsellinoUUID;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getIdMinorenni() {
		return this.idMinorenni;
	}

	public void setIdMinorenni(long idMinorenni) {
		this.idMinorenni = idMinorenni;
	}

	public float getImporto() {
		return this.importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}