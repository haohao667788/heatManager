package org.heatmanagment.spring.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class BoundOut {

	private int start;
	private int limit;

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
