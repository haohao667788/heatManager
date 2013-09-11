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
 * AccountrangeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNTRANGE_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = {
		"USRID", "FINACERANGE" }))
public class AccountrangeInfo implements java.io.Serializable {

	// Fields

	private String accrangeid;
	private UsersInfo usersInfo;
	private Double curbalance;
	private Double curcharge;
	private Double curmoney;
	private Timestamp lastdate;
	private String finacerange;
	private String donefinacerange;

	// Constructors

	/** default constructor */
	public AccountrangeInfo() {
	}

	/** minimal constructor */
	public AccountrangeInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	/** full constructor */
	public AccountrangeInfo(UsersInfo usersInfo, Double curbalance,
			Double curcharge, Double curmoney, Timestamp lastdate,
			String finacerange, String donefinacerange) {
		this.usersInfo = usersInfo;
		this.curbalance = curbalance;
		this.curcharge = curcharge;
		this.curmoney = curmoney;
		this.lastdate = lastdate;
		this.finacerange = finacerange;
		this.donefinacerange = donefinacerange;
	}

	// Property accessors
	@SequenceGenerator(name = "ACCRANGE_ID", allocationSize = 1, sequenceName = "ACCRANGE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCRANGE_ID")
	@Column(name = "ACCRANGEID", unique = true, nullable = false, length = 20)
	public String getAccrangeid() {
		return this.accrangeid;
	}

	public void setAccrangeid(String accrangeid) {
		this.accrangeid = accrangeid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USRID", nullable = false)
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

	@Column(name = "FINACERANGE", length = 20)
	public String getFinacerange() {
		return this.finacerange;
	}

	public void setFinacerange(String finacerange) {
		this.finacerange = finacerange;
	}

	@Column(name = "DONEFINACERANGE", length = 20)
	public String getDonefinacerange() {
		return this.donefinacerange;
	}

	public void setDonefinacerange(String donefinacerange) {
		this.donefinacerange = donefinacerange;
	}

}