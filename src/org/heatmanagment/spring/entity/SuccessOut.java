package org.heatmanagment.spring.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class SuccessOut {

	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
