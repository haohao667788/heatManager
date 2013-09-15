package org.heatmanagment.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * DistrictInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DISTRICT_INFO", schema = "HEATMGR")
public class DistrictInfo implements java.io.Serializable {

	// Fields

	private Long dstid;
	private String dstname;
	private String desp;
	private Boolean isvalid;
	@JsonIgnore
	private Set<ProjectInfo> projectInfos = new HashSet<ProjectInfo>(0);
	@JsonIgnore
	private Set<HeatsourceInfo> heatsourceInfos = new HashSet<HeatsourceInfo>(0);

	// Constructors

	/** default constructor */
	public DistrictInfo() {
	}

	/** minimal constructor */
	public DistrictInfo(String dstname) {
		this.dstname = dstname;
	}

	/** full constructor */
	public DistrictInfo(String dstname, String desp, Boolean isvalid,
			Set<ProjectInfo> projectInfos, Set<HeatsourceInfo> heatsourceInfos) {
		this.dstname = dstname;
		this.desp = desp;
		this.isvalid = isvalid;
		this.projectInfos = projectInfos;
		this.heatsourceInfos = heatsourceInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "DST_ID", allocationSize = 1, sequenceName = "DST_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DST_ID")
	@Column(name = "DSTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getDstid() {
		return this.dstid;
	}

	public void setDstid(Long dstid) {
		this.dstid = dstid;
	}

	@Column(name = "DSTNAME", nullable = true, length = 40)
	public String getDstname() {
		return this.dstname;
	}

	public void setDstname(String dstname) {
		this.dstname = dstname;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "districtInfo")
	public Set<ProjectInfo> getProjectInfos() {
		return this.projectInfos;
	}

	public void setProjectInfos(Set<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "districtInfo")
	public Set<HeatsourceInfo> getHeatsourceInfos() {
		return this.heatsourceInfos;
	}

	public void setHeatsourceInfos(Set<HeatsourceInfo> heatsourceInfos) {
		this.heatsourceInfos = heatsourceInfos;
	}

}