package org.heatmanagment.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * DistrictInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DISTRICT_INFO", schema = "HEATMGR")
public class DistrictInfo implements java.io.Serializable {

	// Fields

	private Long dstid;
	private CountyInfo countyInfo;
	private String dstname;
	private String desp;
	private String comm;
	private Set<CommunityInfo> communityInfos = new HashSet<CommunityInfo>(0);
	private Set<HeatsourceInfo> heatsourceInfos = new HashSet<HeatsourceInfo>(0);
	private Set<ProjectInfo> projectInfos = new HashSet<ProjectInfo>(0);

	// Constructors

	/** default constructor */
	public DistrictInfo() {
	}

	/** minimal constructor */
	public DistrictInfo(CountyInfo countyInfo, String dstname) {
		this.countyInfo = countyInfo;
		this.dstname = dstname;
	}

	/** full constructor */
	public DistrictInfo(CountyInfo countyInfo, String dstname, String desp,
			String comm, Set<CommunityInfo> communityInfos,
			Set<HeatsourceInfo> heatsourceInfos, Set<ProjectInfo> projectInfos) {
		this.countyInfo = countyInfo;
		this.dstname = dstname;
		this.desp = desp;
		this.comm = comm;
		this.communityInfos = communityInfos;
		this.heatsourceInfos = heatsourceInfos;
		this.projectInfos = projectInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "DSTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getDstid() {
		return this.dstid;
	}

	public void setDstid(Long dstid) {
		this.dstid = dstid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CTYID", nullable = false)
	public CountyInfo getCountyInfo() {
		return this.countyInfo;
	}

	public void setCountyInfo(CountyInfo countyInfo) {
		this.countyInfo = countyInfo;
	}

	@Column(name = "DSTNAME", nullable = false, length = 40)
	public String getDstname() {
		return this.dstname;
	}

	public void setDstname(String dstname) {
		this.dstname = dstname;
	}

	@Column(name = "DESP", length = 200)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "districtInfo")
	public Set<CommunityInfo> getCommunityInfos() {
		return this.communityInfos;
	}

	public void setCommunityInfos(Set<CommunityInfo> communityInfos) {
		this.communityInfos = communityInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "districtInfo")
	public Set<HeatsourceInfo> getHeatsourceInfos() {
		return this.heatsourceInfos;
	}

	public void setHeatsourceInfos(Set<HeatsourceInfo> heatsourceInfos) {
		this.heatsourceInfos = heatsourceInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "districtInfo")
	public Set<ProjectInfo> getProjectInfos() {
		return this.projectInfos;
	}

	public void setProjectInfos(Set<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

}