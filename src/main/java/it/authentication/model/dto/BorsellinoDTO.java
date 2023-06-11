package it.authentication.model.dto;

import java.util.UUID;

public class BorsellinoDTO {
	private float importo;

	private String note;
	
	private UUID minorenneUUID;

	public BorsellinoDTO() {
		super();
	}

	public float getImporto() {
		return importo;
	}

	public void setImporto(float importo) {
		this.importo = importo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public UUID getMinorenneUUID() {
		return minorenneUUID;
	}

	public void setMinorenneUUID(UUID minorenneUUID) {
		this.minorenneUUID = minorenneUUID;
	}
	
	

}
