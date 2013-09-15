package org.heatmanagment.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PjtStfMap entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PJT_STF_MAP", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = {
		"PJTID", "STFID" }))
public class PjtStfMap implements java.io.Serializable {

	// Fields

	private Long mapid;
	private StaffInfo staffInfo;
	private ProjectInfo projectInfo;
	private String desp;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public PjtStfMap() {
	}

	/** full constructor */
	public PjtStfMap(StaffInfo staffInfo, ProjectInfo projectInfo, String desp,
			Boolean isvalid) {
		this.staffInfo = staffInfo;
		this.projectInfo = projectInfo;
		this.desp = desp;
		this.isvalid = isvalid;
	}

	// Property accessors
	@SequenceGenerator(name = "PJT_STF_MAP_ID", allocationSize = 1, sequenceName = "PJT_STF_MAP_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PJT_STF_MAP_ID")
	@Column(name = "MAPID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getMapid() {
		return this.mapid;
	}

	public void setMapid(Long mapid) {
		this.mapid = mapid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STFID")
	public StaffInfo getStaffInfo() {
		return this.staffInfo;
	}

	public void setStaffInfo(StaffInfo staffInfo) {
		this.staffInfo = staffInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PJTID")
	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
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