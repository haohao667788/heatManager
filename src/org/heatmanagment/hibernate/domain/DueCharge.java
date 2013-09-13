package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * DueCharge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DUE_CHARGE", schema = "HEATMGR")
public class DueCharge implements java.io.Serializable {

	// Fields

	private Long chgid;
	private UsersInfo usersInfo;
	private String dealname;
	private String dealmonth;
	private String chgtype;
	private Double chgnumber;
	private Double realchgnumber;
	private Timestamp lastchgtime;
	private Set<DueChargeRecordMapping> dueChargeRecordMappings = new HashSet<DueChargeRecordMapping>(
			0);

	// Constructors

	/** default constructor */
	public DueCharge() {
	}

	/** minimal constructor */
	public DueCharge(UsersInfo usersInfo, String dealname, String dealmonth) {
		this.usersInfo = usersInfo;
		this.dealname = dealname;
		this.dealmonth = dealmonth;
	}

	/** full constructor */
	public DueCharge(UsersInfo usersInfo, String dealname, String dealmonth,
			String chgtype, Double chgnumber, Double realchgnumber,
			Timestamp lastchgtime,
			Set<DueChargeRecordMapping> dueChargeRecordMappings) {
		this.usersInfo = usersInfo;
		this.dealname = dealname;
		this.dealmonth = dealmonth;
		this.chgtype = chgtype;
		this.chgnumber = chgnumber;
		this.realchgnumber = realchgnumber;
		this.lastchgtime = lastchgtime;
		this.dueChargeRecordMappings = dueChargeRecordMappings;
	}

	// Property accessors
	@SequenceGenerator(name = "DUECHARGE_ID",allocationSize = 1, sequenceName = "DUECHARGE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DUECHARGE_ID")
	@Column(name = "CHGID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getChgid() {
		return this.chgid;
	}

	public void setChgid(Long chgid) {
		this.chgid = chgid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USRID", nullable = true)
	public UsersInfo getUsersInfo() {
		return this.usersInfo;
	}

	public void setUsersInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	@Column(name = "DEALNAME", nullable = true, length = 20)
	public String getDealname() {
		return this.dealname;
	}

	public void setDealname(String dealname) {
		this.dealname = dealname;
	}

	@Column(name = "DEALMONTH", nullable = true, length = 20)
	public String getDealmonth() {
		return this.dealmonth;
	}

	public void setDealmonth(String dealmonth) {
		this.dealmonth = dealmonth;
	}

	@Column(name = "CHGTYPE", length = 20)
	public String getChgtype() {
		return this.chgtype;
	}

	public void setChgtype(String chgtype) {
		this.chgtype = chgtype;
	}

	@Column(name = "CHGNUMBER", precision = 8)
	public Double getChgnumber() {
		return this.chgnumber;
	}

	public void setChgnumber(Double chgnumber) {
		this.chgnumber = chgnumber;
	}

	@Column(name = "REALCHGNUMBER", precision = 8)
	public Double getRealchgnumber() {
		return this.realchgnumber;
	}

	public void setRealchgnumber(Double realchgnumber) {
		this.realchgnumber = realchgnumber;
	}

	@Column(name = "LASTCHGTIME", length = 7)
	public Timestamp getLastchgtime() {
		return this.lastchgtime;
	}

	public void setLastchgtime(Timestamp lastchgtime) {
		this.lastchgtime = lastchgtime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dueCharge")
	public Set<DueChargeRecordMapping> getDueChargeRecordMappings() {
		return this.dueChargeRecordMappings;
	}

	public void setDueChargeRecordMappings(
			Set<DueChargeRecordMapping> dueChargeRecordMappings) {
		this.dueChargeRecordMappings = dueChargeRecordMappings;
	}

}