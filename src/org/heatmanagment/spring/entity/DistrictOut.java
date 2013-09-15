package org.heatmanagment.spring.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class DistrictOut {
	private Long dstid;
	private String dstname;
	private String desp;

	public Long getDstid() {
		return dstid;
	}

	public void setDstid(Long dstid) {
		this.dstid = dstid;
	}

	public String getDstname() {
		return dstname;
	}

	public void setDstname(String dstname) {
		this.dstname = dstname;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}
}
