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
	private Double balance;
	private Double money;
	private Timestamp lastdate;
	private String dealname;
	private String desp;
	private Boolean isvalid;

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
	public DealInfo(UsersInfo usersInfo, Double balance, Double money,
			Timestamp lastdate, String dealname, String desp, Boolean isvalid) {
		this.usersInfo = usersInfo;
		this.balance = balance;
		this.money = money;
		this.lastdate = lastdate;
		this.dealname = dealname;
		this.desp = desp;
		this.isvalid = isvalid;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USRID", nullable = true)
	public UsersInfo getUsersInfo() {
		return this.usersInfo;
	}

	public void setUsersInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	@Column(name = "BALANCE", precision = 10)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "MONEY", precision = 10)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "LASTDATE", length = 7)
	public Timestamp getLastdate() {
		return this.lastdate;
	}

	public void setLastdate(Timestamp lastdate) {
		this.lastdate = lastdate;
	}

	@Column(name = "DEALNAME", nullable = true, length = 200)
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

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

}