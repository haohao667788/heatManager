package org.heatmanagment.spring.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class UnitOut {

	private Long untid;
	private Long bldid;
	private String bldname;
	private Long cmtid;
	private String cmtname;
	private String untname;
	private String gis;
	private String picaddress;
	private String desp;

	public Long getUntid() {
		return untid;
	}

	public void setUntid(Long untid) {
		this.untid = untid;
	}

	public Long getBldid() {
		return bldid;
	}

	public void setBldid(Long bldid) {
		this.bldid = bldid;
	}

	public String getBldname() {
		return bldname;
	}

	public void setBldname(String bldname) {
		this.bldname = bldname;
	}

	public Long getCmtid() {
		return cmtid;
	}

	public void setCmtid(Long cmtid) {
		this.cmtid = cmtid;
	}

	public String getCmtname() {
		return cmtname;
	}

	public void setCmtname(String cmtname) {
		this.cmtname = cmtname;
	}

	public String getUntname() {
		return untname;
	}

	public void setUntname(String untname) {
		this.untname = untname;
	}

	public String getGis() {
		return gis;
	}

	public void setGis(String gis) {
		this.gis = gis;
	}

	public String getPicaddress() {
		return picaddress;
	}

	public void setPicaddress(String picaddress) {
		this.picaddress = picaddress;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}
}
