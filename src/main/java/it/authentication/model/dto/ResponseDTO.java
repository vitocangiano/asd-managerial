package it.authentication.model.dto;

import java.util.HashMap;
import java.util.Map;

public class ResponseDTO {
	private Boolean status = true;
	private String message = "";
	private String error = "";
	private Map<String, Object> content = new HashMap<String, Object>();

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
