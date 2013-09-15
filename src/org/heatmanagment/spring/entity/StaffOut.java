package org.heatmanagment.spring.entity;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.heatmanagment.hibernate.util.DateConverter;

@JsonAutoDetect
public class StaffOut {
	private Long stfid;
	private String stfname;
	private String stfnumber;
	private String startdate;
	private String phone;
	private String loginname;
	private String pwd;
	private String department;
	private Long verifytype;

	public Long getStfid() {
		return stfid;
	}

	public void setStfid(Long stfid) {
		this.stfid = stfid;
	}

	public String getStfname() {
		return stfname;
	}

	public void setStfname(String stfname) {
		this.stfname = stfname;
	}

	public String getStfnumber() {
		return stfnumber;
	}

	public void setStfnumber(String stfnumber) {
		this.stfnumber = stfnumber;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp start) {
		this.startdate = DateConverter.convert(start);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getVerifytype() {
		return verifytype;
	}

	public void setVerifytype(Long verifytype) {
		this.verifytype = verifytype;
	}

}
