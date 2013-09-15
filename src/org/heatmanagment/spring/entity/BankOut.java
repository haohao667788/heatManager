package org.heatmanagment.spring.entity;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class BankOut {

	private Long bnkid;
	private Long crsid;
	private String crsname;
	private String bnknum;
	private String bnkname;
	private String accountnum;
	private String desp;

	public Long getBnkid() {
		return bnkid;
	}

	public void setBnkid(Long bnkid) {
		this.bnkid = bnkid;
	}

	public Long getCrsid() {
		return crsid;
	}

	public void setCrsid(Long crsid) {
		this.crsid = crsid;
	}

	public String getCrsname() {
		return crsname;
	}

	public void setCrsname(String crsname) {
		this.crsname = crsname;
	}

	public String getBnknum() {
		return bnknum;
	}

	public void setBnknum(String bnknum) {
		this.bnknum = bnknum;
	}

	public String getBnkname() {
		return bnkname;
	}

	public void setBnkname(String bnkname) {
		this.bnkname = bnkname;
	}

	public String getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

}
