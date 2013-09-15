package org.heatmanagment.spring.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class HeatOut {

	private Long srcid;
	private Long dstid;
	private String dstname;
	private String srcname;
	private String srcaddress;
	private String heattype;
	private String desp;

	public Long getSrcid() {
		return srcid;
	}

	public void setSrcid(Long srcid) {
		this.srcid = srcid;
	}

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

	public String getSrcname() {
		return srcname;
	}

	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}

	public String getSrcaddress() {
		return srcaddress;
	}

	public void setSrcaddress(String srcaddress) {
		this.srcaddress = srcaddress;
	}

	public String getHeattype() {
		return heattype;
	}

	public void setHeattype(String heattype) {
		this.heattype = heattype;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}
}
