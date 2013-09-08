package org.heatmanagment.spring.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.heatmanagment.hibernate.domain.DistrictInfo;

@JsonAutoDetect
public class DistrictOut {

	private boolean success;
	private String message;
	private int totalProperty;
	private List<DistrictInfo> data;

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

	public int getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(int totalProperty) {
		this.totalProperty = totalProperty;
	}

	public List<DistrictInfo> getData() {
		return data;
	}

	public void setData(List<DistrictInfo> data) {
		this.data = data;
	}
}
