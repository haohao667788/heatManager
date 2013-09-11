package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * StaffInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STAFF_INFO", schema = "HEATMGR")
public class StaffInfo implements java.io.Serializable {

	// Fields

	private String stfnumber;
	private String stfname;
	private Timestamp startdate;
	private String contactnumber;
	private String loginname;
	private String pwd;
	private Set<LoginInfo> loginInfos = new HashSet<LoginInfo>(0);

	// Constructors

	/** default constructor */
	public StaffInfo() {
	}

	/** full constructor */
	public StaffInfo(String stfname, Timestamp startdate, String contactnumber,
			String loginname, String pwd, Set<LoginInfo> loginInfos) {
		this.stfname = stfname;
		this.startdate = startdate;
		this.contactnumber = contactnumber;
		this.loginname = loginname;
		this.pwd = pwd;
		this.loginInfos = loginInfos;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "STFNUMBER", unique = true, nullable = false, length = 20)
	public String getStfnumber() {
		return this.stfnumber;
	}

	public void setStfnumber(String stfnumber) {
		this.stfnumber = stfnumber;
	}

	@Column(name = "STFNAME", length = 20)
	public String getStfname() {
		return this.stfname;
	}

	public void setStfname(String stfname) {
		this.stfname = stfname;
	}

	@Column(name = "STARTDATE", length = 7)
	public Timestamp getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	@Column(name = "CONTACTNUMBER", length = 20)
	public String getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	@Column(name = "LOGINNAME", length = 20)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "PWD", length = 20)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staffInfo")
	public Set<LoginInfo> getLoginInfos() {
		return this.loginInfos;
	}

	public void setLoginInfos(Set<LoginInfo> loginInfos) {
		this.loginInfos = loginInfos;
	}

}