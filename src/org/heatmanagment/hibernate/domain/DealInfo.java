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
import javax.persistence.UniqueConstraint;

/**
 * DealInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEAL_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = {
		"USRID", "DEALNAME" }))
public class DealInfo implements java.io.Serializable {

	// Fields

	private Long dealid;
	private UsersInfo usersInfo;
	private Double curbalance;
	private Double curcharge;
	private Double curmoney;
	private Timestamp lastdate;
	private String dealname;
	private String desp;

	// Constructors

	/** default constructor */
	public DealInfo() {
	}

	/** minimal constructor */
	public DealInfo(UsersInfo usersInfo, String dealname) {
		this.usersInfo = usersInfo;
		this.dealname = dealname;
	}

	/** full constructor */
	public DealInfo(UsersInfo usersInfo, Double curbalance, Double curcharge,
			Double curmoney, Timestamp lastdate, String dealname, String desp) {
		this.usersInfo = usersInfo;
		this.curbalance = curbalance;
		this.curcharge = curcharge;
		this.curmoney = curmoney;
		this.lastdate = lastdate;
		this.dealname = dealname;
		this.desp = desp;
	}

	// Property accessors
	@SequenceGenerator(name = "DEAL_ID", allocationSize = 1, sequenceName = "DEAL_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DEAL_ID")
	@Column(name = "DEALID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getDealid() {
		return this.dealid;
	}

	public void setDealid(Long dealid) {
		this.dealid = dealid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USRID", nullable = true)
	public UsersInfo getUsersInfo() {
		return this.usersInfo;
	}

	public void setUsersInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	@Column(name = "CURBALANCE", precision = 10)
	public Double getCurbalance() {
		return this.curbalance;
	}

	public void setCurbalance(Double curbalance) {
		this.curbalance = curbalance;
	}

	@Column(name = "CURCHARGE", precision = 10)
	public Double getCurcharge() {
		return this.curcharge;
	}

	public void setCurcharge(Double curcharge) {
		this.curcharge = curcharge;
	}

	@Column(name = "CURMONEY", precision = 10)
	public Double getCurmoney() {
		return this.curmoney;
	}

	public void setCurmoney(Double curmoney) {
		this.curmoney = curmoney;
	}

	@Column(name = "LASTDATE", length = 7)
	public Timestamp getLastdate() {
		return this.lastdate;
	}

	public void setLastdate(Timestamp lastdate) {
		this.lastdate = lastdate;
	}

	@Column(name = "DEALNAME", nullable = true, length = 20)
	public String getDealname() {
		return this.dealname;
	}

	public void setDealname(String dealname) {
		this.dealname = dealname;
	}

	@Column(name = "DESP", length = 2000)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

}