package org.heatmanagment.spring.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class CourseOut {

	private Long crsid;
	private String crsnum;
	private String crsname;
	private String desp;
	private String dealname;

	public Long getCrsid() {
		return crsid;
	}

	public void setCrsid(Long crsid) {
		this.crsid = crsid;
	}

	public String getCrsnum() {
		return crsnum;
	}

	public void setCrsnum(String crsnum) {
		this.crsnum = crsnum;
	}

	public String getCrsname() {
		return crsname;
	}

	public void setCrsname(String crsname) {
		this.crsname = crsname;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public String getDealname() {
		return dealname;
	}

	public void setDealname(String dealname) {
		this.dealname = dealname;
	}

}
