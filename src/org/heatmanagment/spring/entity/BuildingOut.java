package org.heatmanagment.spring.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class BuildingOut {

	private Long bldid;
	private Long mchid;
	private String mchname;
	private Long cmtid;
	private String cmtname;
	private String bldname;
	private String bldaddress;
	private String heattype;
	private String gis;
	private String picaddress;
	private String desp;

	public Long getBldid() {
		return bldid;
	}

	public void setBldid(Long bldid) {
		this.bldid = bldid;
	}

	public Long getMchid() {
		return mchid;
	}

	public void setMchid(Long mchid) {
		this.mchid = mchid;
	}

	public String getMchname() {
		return mchname;
	}

	public void setMchname(String mchname) {
		this.mchname = mchname;
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

	public String getBldname() {
		return bldname;
	}

	public void setBldname(String bldname) {
		this.bldname = bldname;
	}

	public String getBldaddress() {
		return bldaddress;
	}

	public void setBldaddress(String bldaddress) {
		this.bldaddress = bldaddress;
	}

	public String getHeattype() {
		return heattype;
	}

	public void setHeattype(String heattype) {
		this.heattype = heattype;
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
