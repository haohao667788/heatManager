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
 * ChargeRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHARGE_RECORD", schema = "HEATMGR")
public class ChargeRecord implements java.io.Serializable {

	// Fields

	private Long rcdid;
	private StaffInfo staffInfo;
	private UsersInfo usersInfo;
	private BankCertificate bankCertificate;
	private Timestamp rcdtime;
	private Double money;
	private String chgtype;
	private String checknum;
	private String rcdpic;
	private String dealname;
	private Long financechecker;
	private String desp;
	private Timestamp chargeverifytime;
	private Boolean isvalid;
	private Set<DueChargeRecordMapping> dueChargeRecordMappings = new HashSet<DueChargeRecordMapping>(
			0);

	// Constructors

	/** default constructor */
	public ChargeRecord() {
	}

	/** minimal constructor */
	public ChargeRecord(StaffInfo staffInfo, UsersInfo usersInfo) {
		this.staffInfo = staffInfo;
		this.usersInfo = usersInfo;
	}

	/** full constructor */
	public ChargeRecord(StaffInfo staffInfo, UsersInfo usersInfo,
			BankCertificate bankCertificate, Timestamp rcdtime, Double money,
			String chgtype, String checknum, String rcdpic, String dealname,
			Long financechecker, String desp, Timestamp chargeverifytime,
			Boolean isvalid, Set<DueChargeRecordMapping> dueChargeRecordMappings) {
		this.staffInfo = staffInfo;
		this.usersInfo = usersInfo;
		this.bankCertificate = bankCertificate;
		this.rcdtime = rcdtime;
		this.money = money;
		this.chgtype = chgtype;
		this.checknum = checknum;
		this.rcdpic = rcdpic;
		this.dealname = dealname;
		this.financechecker = financechecker;
		this.desp = desp;
		this.chargeverifytime = chargeverifytime;
		this.isvalid = isvalid;
		this.dueChargeRecordMappings = dueChargeRecordMappings;
	}

	// Property accessors
	@SequenceGenerator(name = "RECORD_ID", allocationSize = 1, sequenceName = "RECORD_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RECORD_ID")
	@Column(name = "RCDID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getRcdid() {
		return this.rcdid;
	}

	public void setRcdid(Long rcdid) {
		this.rcdid = rcdid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STFID", nullable = true)
	public StaffInfo getStaffInfo() {
		return this.staffInfo;
	}

	public void setStaffInfo(StaffInfo staffInfo) {
		this.staffInfo = staffInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USRID", nullable = true)
	public UsersInfo getUsersInfo() {
		return this.usersInfo;
	}

	public void setUsersInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CTFID")
	public BankCertificate getBankCertificate() {
		return this.bankCertificate;
	}

	public void setBankCertificate(BankCertificate bankCertificate) {
		this.bankCertificate = bankCertificate;
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

	@Column(name = "RCDPIC", length = 200)
	public String getRcdpic() {
		return this.rcdpic;
	}

	public void setRcdpic(String rcdpic) {
		this.rcdpic = rcdpic;
	}

	@Column(name = "DEALNAME", length = 200)
	public String getDealname() {
		return this.dealname;
	}

	public void setDealname(String dealname) {
		this.dealname = dealname;
	}

	@Column(name = "FINANCECHECKER", precision = 10, scale = 0)
	public Long getFinancechecker() {
		return this.financechecker;
	}

	public void setFinancechecker(Long financechecker) {
		this.financechecker = financechecker;
	}

	@Column(name = "DESP", length = 2000)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	@Column(name = "CHARGEVERIFYTIME", length = 7)
	public Timestamp getChargeverifytime() {
		return this.chargeverifytime;
	}

	public void setChargeverifytime(Timestamp chargeverifytime) {
		this.chargeverifytime = chargeverifytime;
	}

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "chargeRecord")
	public Set<DueChargeRecordMapping> getDueChargeRecordMappings() {
		return this.dueChargeRecordMappings;
	}

	public void setDueChargeRecordMappings(
			Set<DueChargeRecordMapping> dueChargeRecordMappings) {
		this.dueChargeRecordMappings = dueChargeRecordMappings;
	}

}