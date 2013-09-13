package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * StaffInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STAFF_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = "STFNUMBER"))
public class StaffInfo implements java.io.Serializable {

	// Fields

	private Long stfid;
	private String stfname;
	private String stfnumber;
	private Timestamp startdate;
	private String phone;
	private String loginname;
	private String pwd;
	private String department;
	private Long verifytype;
	private Set<LoginInfo> loginInfos = new HashSet<LoginInfo>(0);
	private Set<ChargeRecord> chargeRecords = new HashSet<ChargeRecord>(0);

	// Constructors

	/** default constructor */
	public StaffInfo() {
	}

	/** full constructor */
	public StaffInfo(String stfname, String stfnumber, Timestamp startdate,
			String phone, String loginname, String pwd, String department,
			Long verifytype, Set<LoginInfo> loginInfos,
			Set<ChargeRecord> chargeRecords) {
		this.stfname = stfname;
		this.stfnumber = stfnumber;
		this.startdate = startdate;
		this.phone = phone;
		this.loginname = loginname;
		this.pwd = pwd;
		this.department = department;
		this.verifytype = verifytype;
		this.loginInfos = loginInfos;
		this.chargeRecords = chargeRecords;
	}

	// Property accessors
	@SequenceGenerator(name = "STAFF_ID", allocationSize = 1, sequenceName = "STAFF_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "STAFF_ID")
	@Column(name = "STFID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getStfid() {
		return this.stfid;
	}

	public void setStfid(Long stfid) {
		this.stfid = stfid;
	}

	@Column(name = "STFNAME", length = 20)
	public String getStfname() {
		return this.stfname;
	}

	public void setStfname(String stfname) {
		this.stfname = stfname;
	}

	@Column(name = "STFNUMBER", unique = true, length = 20)
	public String getStfnumber() {
		return this.stfnumber;
	}

	public void setStfnumber(String stfnumber) {
		this.stfnumber = stfnumber;
	}

	@Column(name = "STARTDATE", length = 7)
	public Timestamp getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	@Column(name = "DEPARTMENT", length = 20)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "VERIFYTYPE", precision = 10, scale = 0)
	public Long getVerifytype() {
		return this.verifytype;
	}

	public void setVerifytype(Long verifytype) {
		this.verifytype = verifytype;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "staffInfo")
	public Set<LoginInfo> getLoginInfos() {
		return this.loginInfos;
	}

	public void setLoginInfos(Set<LoginInfo> loginInfos) {
		this.loginInfos = loginInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "staffInfo")
	public Set<ChargeRecord> getChargeRecords() {
		return this.chargeRecords;
	}

	public void setChargeRecords(Set<ChargeRecord> chargeRecords) {
		this.chargeRecords = chargeRecords;
	}

}