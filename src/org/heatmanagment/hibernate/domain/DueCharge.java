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
	private Double charge;
	private String dealname;
	private String chgtype;
	private Double area;
	private Double rate;
	private Double reducechg;
	private Double money;
	private Timestamp lastchgtime;
	private Boolean isvalid;
	private Set<DueChargeRecordMapping> dueChargeRecordMappings = new HashSet<DueChargeRecordMapping>(
			0);

	// Constructors

	/** default constructor */
	public DueCharge() {
	}

	/** minimal constructor */
	public DueCharge(UsersInfo usersInfo, String dealname) {
		this.usersInfo = usersInfo;
		this.dealname = dealname;
	}

	/** full constructor */
	public DueCharge(UsersInfo usersInfo, Double charge, String dealname,
			String chgtype, Double area, Double rate, Double reducechg,
			Double money, Timestamp lastchgtime, Boolean isvalid,
			Set<DueChargeRecordMapping> dueChargeRecordMappings) {
		this.usersInfo = usersInfo;
		this.charge = charge;
		this.dealname = dealname;
		this.chgtype = chgtype;
		this.area = area;
		this.rate = rate;
		this.reducechg = reducechg;
		this.money = money;
		this.lastchgtime = lastchgtime;
		this.isvalid = isvalid;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USRID", nullable = true)
	public UsersInfo getUsersInfo() {
		return this.usersInfo;
	}

	public void setUsersInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	@Column(name = "CHARGE", precision = 8)
	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	@Column(name = "DEALNAME", nullable = true, length = 200)
	public String getDealname() {
		return this.dealname;
	}

	public void setDealname(String dealname) {
		this.dealname = dealname;
	}

	@Column(name = "CHGTYPE", length = 20)
	public String getChgtype() {
		return this.chgtype;
	}

	public void setChgtype(String chgtype) {
		this.chgtype = chgtype;
	}

	@Column(name = "AREA", precision = 8)
	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "RATE", precision = 8)
	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Column(name = "REDUCECHG", precision = 8)
	public Double getReducechg() {
		return this.reducechg;
	}

	public void setReducechg(Double reducechg) {
		this.reducechg = reducechg;
	}

	@Column(name = "MONEY", precision = 8)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "LASTCHGTIME", length = 7)
	public Timestamp getLastchgtime() {
		return this.lastchgtime;
	}

	public void setLastchgtime(Timestamp lastchgtime) {
		this.lastchgtime = lastchgtime;
	}

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
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