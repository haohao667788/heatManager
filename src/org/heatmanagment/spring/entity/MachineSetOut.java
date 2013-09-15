package org.heatmanagment.spring.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class MachineSetOut {

	private Long mchid;
	private Long srcid;
	private String srcname;
	private Long clsid;
	private String clsname;
	private String mchname;
	private String gis;
	private String desp;

	public Long getMchid() {
		return mchid;
	}

	public void setMchid(Long mchid) {
		this.mchid = mchid;
	}

	public Long getSrcid() {
		return srcid;
	}

	public void setSrcid(Long srcid) {
		this.srcid = srcid;
	}

	public String getSrcname() {
		return srcname;
	}

	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}

	public Long getClsid() {
		return clsid;
	}

	public void setClsid(Long clsid) {
		this.clsid = clsid;
	}

	public String getClsname() {
		return clsname;
	}

	public void setClsname(String clsname) {
		this.clsname = clsname;
	}

	public String getMchname() {
		return mchname;
	}

	public void setMchname(String mchname) {
		this.mchname = mchname;
	}

	public String getGis() {
		return gis;
	}

	public void setGis(String gis) {
		this.gis = gis;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

}
