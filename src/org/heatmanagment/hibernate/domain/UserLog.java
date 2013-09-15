package org.heatmanagment.hibernate.domain;

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
 * UserLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USER_LOG", schema = "HEATMGR")
public class UserLog implements java.io.Serializable {

	// Fields

	private Long logid;
	private UsersInfo usersInfo;
	private String logtype;
	private String logtitle;
	private String logcontent;
	private String desp;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public UserLog() {
	}

	/** minimal constructor */
	public UserLog(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	/** full constructor */
	public UserLog(UsersInfo usersInfo, String logtype, String logtitle,
			String logcontent, String desp, Boolean isvalid) {
		this.usersInfo = usersInfo;
		this.logtype = logtype;
		this.logtitle = logtitle;
		this.logcontent = logcontent;
		this.desp = desp;
		this.isvalid = isvalid;
	}

	// Property accessors
	@SequenceGenerator(name = "LOG_ID",allocationSize = 1, sequenceName = "LOG_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LOG_ID")
	@Column(name = "LOGID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getLogid() {
		return this.logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USRID", nullable = true)
	public UsersInfo getUsersInfo() {
		return this.usersInfo;
	}

	public void setUsersInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	@Column(name = "LOGTYPE", length = 20)
	public String getLogtype() {
		return this.logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	@Column(name = "LOGTITLE", length = 20)
	public String getLogtitle() {
		return this.logtitle;
	}

	public void setLogtitle(String logtitle) {
		this.logtitle = logtitle;
	}

	@Column(name = "LOGCONTENT", length = 2000)
	public String getLogcontent() {
		return this.logcontent;
	}

	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}

	@Column(name = "DESP", length = 2000)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

}