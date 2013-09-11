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
 * CertificateChargeMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CERTIFICATE_CHARGE_MAPPING", schema = "HEATMGR")
public class CertificateChargeMapping implements java.io.Serializable {

	// Fields

	private Long ccmappingid;
	private ChargeRecord chargeRecord;
	private BankCertificate bankCertificate;

	// Constructors

	/** default constructor */
	public CertificateChargeMapping() {
	}

	/** full constructor */
	public CertificateChargeMapping(ChargeRecord chargeRecord,
			BankCertificate bankCertificate) {
		this.chargeRecord = chargeRecord;
		this.bankCertificate = bankCertificate;
	}

	// Property accessors
	@SequenceGenerator(name = "CCMAPPING_ID", allocationSize = 1, sequenceName = "CCMAPPING_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CCMAPPING_ID")
	@Column(name = "CCMAPPINGID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCcmappingid() {
		return this.ccmappingid;
	}

	public void setCcmappingid(Long ccmappingid) {
		this.ccmappingid = ccmappingid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RCDID")
	public ChargeRecord getChargeRecord() {
		return this.chargeRecord;
	}

	public void setChargeRecord(ChargeRecord chargeRecord) {
		this.chargeRecord = chargeRecord;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CTFID")
	public BankCertificate getBankCertificate() {
		return this.bankCertificate;
	}

	public void setBankCertificate(BankCertificate bankCertificate) {
		this.bankCertificate = bankCertificate;
	}

}