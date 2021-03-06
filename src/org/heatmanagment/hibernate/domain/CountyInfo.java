package org.heatmanagment.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * CountyInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COUNTY_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = {
		"TOWNNAME", "CITYNAME" }))
public class CountyInfo implements java.io.Serializable {

	// Fields

	private Long ctyid;
	private String townname;
	private String cityname;
	private String desp;
	private Boolean isvalid;
	@JsonIgnore
	private Set<ProjectInfo> projectInfos = new HashSet<ProjectInfo>(0);

	// Constructors

	/** default constructor */
	public CountyInfo() {
	}

	/** minimal constructor */
	public CountyInfo(String townname, String cityname) {
		this.townname = townname;
		this.cityname = cityname;
	}

	/** full constructor */
	public CountyInfo(String townname, String cityname, String desp,
			Boolean isvalid, Set<ProjectInfo> projectInfos) {
		this.townname = townname;
		this.cityname = cityname;
		this.desp = desp;
		this.isvalid = isvalid;
		this.projectInfos = projectInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "CTY_ID", allocationSize = 1, sequenceName = "CTY_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CTY_ID")
	@Column(name = "CTYID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCtyid() {
		return this.ctyid;
	}

	public void setCtyid(Long ctyid) {
		this.ctyid = ctyid;
	}

	@Column(name = "TOWNNAME", nullable = true, length = 200)
	public String getTownname() {
		return this.townname;
	}

	public void setTownname(String townname) {
		this.townname = townname;
	}

	@Column(name = "CITYNAME", nullable = true, length = 200)
	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "countyInfo")
	public Set<ProjectInfo> getProjectInfos() {
		return this.projectInfos;
	}

	public void setProjectInfos(Set<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

}