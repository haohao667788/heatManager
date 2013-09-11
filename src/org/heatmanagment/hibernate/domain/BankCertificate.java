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
	private Timestamp cdate;
	private Timestamp importdate;
	private String importer;
	private Long relatednum;
	private Set<CertificateChargeMapping> certificateChargeMappings = new HashSet<CertificateChargeMapping>(
			0);

	// Constructors

	/** default constructor */
	public BankCertificate() {
	}

	/** full constructor */
	public BankCertificate(BankInfo bankInfo, String ctftype, String ctfnumber,
			Double money, String undertaker, Timestamp cdate,
			Timestamp importdate, String importer, Long relatednum,
			Set<CertificateChargeMapping> certificateChargeMappings) {
		this.bankInfo = bankInfo;
		this.ctftype = ctftype;
		this.ctfnumber = ctfnumber;
		this.money = money;
		this.undertaker = undertaker;
		this.cdate = cdate;
		this.importdate = importdate;
		this.importer = importer;
		this.relatednum = relatednum;
		this.certificateChargeMappings = certificateChargeMappings;
	}

	// Property accessors
	@SequenceGenerator(name = "CERTIFICATE_ID", allocationSize = 1, sequenceName = "CERTIFICATE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CERTIFICATE_ID")
	@Column(name = "CTFID", unique = true, nullable = false, precision = 10, scale = 0)
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

	@Column(name = "CDATE", length = 7)
	public Timestamp getCdate() {
		return this.cdate;
	}

	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bankCertificate")
	public Set<CertificateChargeMapping> getCertificateChargeMappings() {
		return this.certificateChargeMappings;
	}

	public void setCertificateChargeMappings(
			Set<CertificateChargeMapping> certificateChargeMappings) {
		this.certificateChargeMappings = certificateChargeMappings;
	}

}