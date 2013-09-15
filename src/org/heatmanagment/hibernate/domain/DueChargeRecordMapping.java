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
 * DueChargeRecordMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DUE_CHARGE_RECORD_MAPPING", schema = "HEATMGR")
public class DueChargeRecordMapping implements java.io.Serializable {

	// Fields

	private Long mapid;
	private ChargeRecord chargeRecord;
	private DueCharge dueCharge;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public DueChargeRecordMapping() {
	}

	/** full constructor */
	public DueChargeRecordMapping(ChargeRecord chargeRecord,
			DueCharge dueCharge, Boolean isvalid) {
		this.chargeRecord = chargeRecord;
		this.dueCharge = dueCharge;
		this.isvalid = isvalid;
	}

	// Property accessors
	@SequenceGenerator(name = "CHG_RCD_MAPPING_ID",allocationSize = 1, sequenceName = "CHG_RCD_MAPPING_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CHG_RCD_MAPPING_ID")
	@Column(name = "MAPID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getMapid() {
		return this.mapid;
	}

	public void setMapid(Long mapid) {
		this.mapid = mapid;
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
	@JoinColumn(name = "CHGID")
	public DueCharge getDueCharge() {
		return this.dueCharge;
	}

	public void setDueCharge(DueCharge dueCharge) {
		this.dueCharge = dueCharge;
	}

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

}