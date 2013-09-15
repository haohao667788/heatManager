package org.heatmanagment.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ClsStfMap entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CLS_STF_MAP", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CLSID", "STFID" }))
public class ClsStfMap implements java.io.Serializable {

	// Fields

	private Long mapid;
	private StaffInfo staffInfo;
	private ClassInfo classInfo;
	private String desp;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public ClsStfMap() {
	}

	/** full constructor */
	public ClsStfMap(StaffInfo staffInfo, ClassInfo classInfo, String desp,
			Boolean isvalid) {
		this.staffInfo = staffInfo;
		this.classInfo = classInfo;
		this.desp = desp;
		this.isvalid = isvalid;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "MAPID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getMapid() {
		return this.mapid;
	}

	public void setMapid(Long mapid) {
		this.mapid = mapid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STFID")
	public StaffInfo getStaffInfo() {
		return this.staffInfo;
	}

	public void setStaffInfo(StaffInfo staffInfo) {
		this.staffInfo = staffInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLSID")
	public ClassInfo getClassInfo() {
		return this.classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
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