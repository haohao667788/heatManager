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
 * BankCertificate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANK_CERTIFICATE", schema = "HEATMGR")
public class BankCertificate implements java.io.Serializable {

	// Fields

	private Long ctfid;
	private BankInfo bankInfo;
	private String ctftype;
	private String ctfnumber;
	private Double money;
	private String undertaker;
	private Timestamp ctfdate;
	private Timestamp importdate;
	private String importer;
	private Long relatednum;
	private Boolean isvalid;
	private Set<ChargeRecord> chargeRecords = new HashSet<ChargeRecord>(0);

	// Constructors

	/** default constructor */
	public BankCertificate() {
	}

	/** full constructor */
	public BankCertificate(BankInfo bankInfo, String ctftype, String ctfnumber,
			Double money, String undertaker, Timestamp ctfdate,
			Timestamp importdate, String importer, Long relatednum,
			Boolean isvalid, Set<ChargeRecord> chargeRecords) {
		this.bankInfo = bankInfo;
		this.ctftype = ctftype;
		this.ctfnumber = ctfnumber;
		this.money = money;
		this.undertaker = undertaker;
		this.ctfdate = ctfdate;
		this.importdate = importdate;
		this.importer = importer;
		this.relatednum = relatednum;
		this.isvalid = isvalid;
		this.chargeRecords = chargeRecords;
	}

	// Property accessors
	@SequenceGenerator(name = "CERTIFICATE_ID",allocationSize = 1, sequenceName = "CERTIFICATE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CERTIFICATE_ID")
	@Column(name = "CTFID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getCtfid() {
		return this.ctfid;
	}

	public void setCtfid(Long ctfid) {
		this.ctfid = ctfid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BNKID")
	public BankInfo getBankInfo() {
		return this.bankInfo;
	}

	public void setBankInfo(BankInfo bankInfo) {
		this.bankInfo = bankInfo;
	}

	@Column(name = "CTFTYPE", length = 10)
	public String getCtftype() {
		return this.ctftype;
	}

	public void setCtftype(String ctftype) {
		this.ctftype = ctftype;
	}

	@Column(name = "CTFNUMBER", length = 20)
	public String getCtfnumber() {
		return this.ctfnumber;
	}

	public void setCtfnumber(String ctfnumber) {
		this.ctfnumber = ctfnumber;
	}

	@Column(name = "MONEY", precision = 10)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "UNDERTAKER", length = 20)
	public String getUndertaker() {
		return this.undertaker;
	}

	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}

	@Column(name = "CTFDATE", length = 7)
	public Timestamp getCtfdate() {
		return this.ctfdate;
	}

	public void setCtfdate(Timestamp ctfdate) {
		this.ctfdate = ctfdate;
	}

	@Column(name = "IMPORTDATE", length = 7)
	public Timestamp getImportdate() {
		return this.importdate;
	}

	public void setImportdate(Timestamp importdate) {
		this.importdate = importdate;
	}

	@Column(name = "IMPORTER", length = 20)
	public String getImporter() {
		return this.importer;
	}

	public void setImporter(String importer) {
		this.importer = importer;
	}

	@Column(name = "RELATEDNUM", precision = 10, scale = 0)
	public Long getRelatednum() {
		return this.relatednum;
	}

	public void setRelatednum(Long relatednum) {
		this.relatednum = relatednum;
	}

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bankCertificate")
	public Set<ChargeRecord> getChargeRecords() {
		return this.chargeRecords;
	}

	public void setChargeRecords(Set<ChargeRecord> chargeRecords) {
		this.chargeRecords = chargeRecords;
	}

}