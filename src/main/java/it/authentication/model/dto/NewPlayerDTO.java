package it.authentication.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewPlayerDTO {
	private MaggiorenniDTO maggiorenne;
	private List<MinorenniDTO> minorenni;
	public MaggiorenniDTO getMaggiorenne() {
		return maggiorenne;
	}
	public void setMaggiorenne(MaggiorenniDTO maggiorenne) {
		this.maggiorenne = maggiorenne;
	}
	public List<MinorenniDTO> getMinorenni() {
		return minorenni;
	}
	public void setMinorenni(List<MinorenniDTO> minorenni) {
		this.minorenni = minorenni;
	}
	
	public NewPlayerDTO(MaggiorenniDTO maggiorenne, List<MinorenniDTO> minorenni) {
		super();
		this.maggiorenne = maggiorenne;
		this.minorenni = minorenni;
	}
	
	public NewPlayerDTO() {
		super();
	}
	 

	
	

}
