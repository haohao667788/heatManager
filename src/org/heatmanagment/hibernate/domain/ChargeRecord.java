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

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * ChargeRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHARGE_RECORD", schema = "HEATMGR")
@JsonAutoDetect
public class ChargeRecord implements java.io.Serializable {

	// Fields

	private Long rcdid;
	private UsersInfo usersInfo;
	private Long accrangeid;
	private Timestamp rcdtime;
	private Double money;
	private String chgtype;
	private String checknum;
	private String rcdpic;
	private String chgyear;
	private Long chargerid;
	private Long financechecker;
	private Long cid;
	@JsonProperty("desp")
	private String comm;

	// Constructors

	/** default constructor */
	public ChargeRecord() {
	}

	/** minimal constructor */
	public ChargeRecord(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	/** full constructor */
	public ChargeRecord(UsersInfo usersInfo, Long accrangeid,
			Timestamp rcdtime, Double money, String chgtype, String checknum,
			String rcdpic, String chgyear, Long chargerid, Long financechecker,
			Long cid, String comm) {
		this.usersInfo = usersInfo;
		this.accrangeid = accrangeid;
		this.rcdtime = rcdtime;
		this.money = money;
		this.chgtype = chgtype;
		this.checknum = checknum;
		this.rcdpic = rcdpic;
		this.chgyear = chgyear;
		this.chargerid = chargerid;
		this.financechecker = financechecker;
		this.cid = cid;
		this.comm = comm;
	}

	// Property accessors
	@SequenceGenerator(name = "RECORD_ID", allocationSize = 1, sequenceName = "RECORD_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RECORD_ID")
	@Column(name = "RCDID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getRcdid() {
		return this.rcdid;
	}

	public void setRcdid(Long rcdid) {
		this.rcdid = rcdid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USRID", nullable = false)
	public UsersInfo getUsersInfo() {
		return this.usersInfo;
	}

	public void setUsersInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	@Column(name = "ACCRANGEID", precision = 10, scale = 0)
	public Long getAccrangeid() {
		return this.accrangeid;
	}

	public void setAccrangeid(Long accrangeid) {
		this.accrangeid = accrangeid;
	}

	@Column(name = "RCDTIME", length = 7)
	public Timestamp getRcdtime() {
		return this.rcdtime;
	}

	public void setRcdtime(Timestamp rcdtime) {
		this.rcdtime = rcdtime;
	}

	@Column(name = "MONEY", precision = 8)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "CHGTYPE", length = 20)
	public String getChgtype() {
		return this.chgtype;
	}

	public void setChgtype(String chgtype) {
		this.chgtype = chgtype;
	}

	@Column(name = "CHECKNUM", length = 20)
	public String getChecknum() {
		return this.checknum;
	}

	public void setChecknum(String checknum) {
		this.checknum = checknum;
	}

	@Column(name = "RCDPIC", length = 100)
	public String getRcdpic() {
		return this.rcdpic;
	}

	public void setRcdpic(String rcdpic) {
		this.rcdpic = rcdpic;
	}

	@Column(name = "CHGYEAR", length = 20)
	public String getChgyear() {
		return this.chgyear;
	}

	public void setChgyear(String chgyear) {
		this.chgyear = chgyear;
	}

	@Column(name = "CHARGERID", precision = 10, scale = 0)
	public Long getChargerid() {
		return this.chargerid;
	}

	public void setChargerid(Long chargerid) {
		this.chargerid = chargerid;
	}

	@Column(name = "FINANCECHECKER", precision = 10, scale = 0)
	public Long getFinancechecker() {
		return this.financechecker;
	}

	public void setFinancechecker(Long financechecker) {
		this.financechecker = financechecker;
	}

	@Column(name = "CID", precision = 10, scale = 0)
	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

}