package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * LoginInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOGIN_INFO", schema = "HEATMGR")
public class LoginInfo implements java.io.Serializable {

	// Fields

	private Long lginid;
	private StaffInfo staffInfo;
	private Timestamp logindate;
	private String ip;
	private String success;
	private String failrsn;
	private String oltime;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public LoginInfo() {
	}

	/** full constructor */
	public LoginInfo(StaffInfo staffInfo, Timestamp logindate, String ip,
			String success, String failrsn, String oltime, Boolean isvalid) {
		this.staffInfo = staffInfo;
		this.logindate = logindate;
		this.ip = ip;
		this.success = success;
		this.failrsn = failrsn;
		this.oltime = oltime;
		this.isvalid = isvalid;
	}

	// Property accessors
	@SequenceGenerator(name = "LOGIN_ID",allocationSize = 1, sequenceName = "LOGIN_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LOGIN_ID")
	@Column(name = "LGINID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getLginid() {
		return this.lginid;
	}

	public void setLginid(Long lginid) {
		this.lginid = lginid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STFID")
	public StaffInfo getStaffInfo() {
		return this.staffInfo;
	}

	public void setStaffInfo(StaffInfo staffInfo) {
		this.staffInfo = staffInfo;
	}

	@Column(name = "LOGINDATE", length = 7)
	public Timestamp getLogindate() {
		return this.logindate;
	}

	public void setLogindate(Timestamp logindate) {
		this.logindate = logindate;
	}

	@Column(name = "IP", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "SUCCESS", length = 4)
	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	@Column(name = "FAILRSN", length = 2000)
	public String getFailrsn() {
		return this.failrsn;
	}

	public void setFailrsn(String failrsn) {
		this.failrsn = failrsn;
	}

	@Column(name = "OLTIME", length = 20)
	public String getOltime() {
		return this.oltime;
	}

	public void setOltime(String oltime) {
		this.oltime = oltime;
	}

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

}