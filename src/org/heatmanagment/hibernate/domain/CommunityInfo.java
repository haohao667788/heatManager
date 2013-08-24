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
 * CommunityInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMMUNITY_INFO", schema = "HEATMGR")
public class CommunityInfo implements java.io.Serializable {

	// Fields

	private Long cmtid;
	private DistrictInfo districtInfo;
	private ProjectInfo projectInfo;
	private String cmtname;
	private String briefname;
	private String cmtaddress;
	private String gis;
	private String picaddress;
	private String desp;
	private String comm;
	private Set<BuildingInfo> buildingInfos = new HashSet<BuildingInfo>(0);

	// Constructors

	/** default constructor */
	public CommunityInfo() {
	}

	/** minimal constructor */
	public CommunityInfo(ProjectInfo projectInfo, String cmtname,
			String cmtaddress) {
		this.projectInfo = projectInfo;
		this.cmtname = cmtname;
		this.cmtaddress = cmtaddress;
	}

	/** full constructor */
	public CommunityInfo(DistrictInfo districtInfo, ProjectInfo projectInfo,
			String cmtname, String briefname, String cmtaddress, String gis,
			String picaddress, String desp, String comm,
			Set<BuildingInfo> buildingInfos) {
		this.districtInfo = districtInfo;
		this.projectInfo = projectInfo;
		this.cmtname = cmtname;
		this.briefname = briefname;
		this.cmtaddress = cmtaddress;
		this.gis = gis;
		this.picaddress = picaddress;
		this.desp = desp;
		this.comm = comm;
		this.buildingInfos = buildingInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "CMTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCmtid() {
		return this.cmtid;
	}

	public void setCmtid(Long cmtid) {
		this.cmtid = cmtid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DSTID")
	public DistrictInfo getDistrictInfo() {
		return this.districtInfo;
	}

	public void setDistrictInfo(DistrictInfo districtInfo) {
		this.districtInfo = districtInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PJTID", nullable = false)
	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Column(name = "CMTNAME", nullable = false, length = 20)
	public String getCmtname() {
		return this.cmtname;
	}

	public void setCmtname(String cmtname) {
		this.cmtname = cmtname;
	}

	@Column(name = "BRIEFNAME", length = 10)
	public String getBriefname() {
		return this.briefname;
	}

	public void setBriefname(String briefname) {
		this.briefname = briefname;
	}

	@Column(name = "CMTADDRESS", nullable = false, length = 200)
	public String getCmtaddress() {
		return this.cmtaddress;
	}

	public void setCmtaddress(String cmtaddress) {
		this.cmtaddress = cmtaddress;
	}

	@Column(name = "GIS", length = 20)
	public String getGis() {
		return this.gis;
	}

	public void setGis(String gis) {
		this.gis = gis;
	}

	@Column(name = "PICADDRESS", length = 100)
	public String getPicaddress() {
		return this.picaddress;
	}

	public void setPicaddress(String picaddress) {
		this.picaddress = picaddress;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "communityInfo")
	public Set<BuildingInfo> getBuildingInfos() {
		return this.buildingInfos;
	}

	public void setBuildingInfos(Set<BuildingInfo> buildingInfos) {
		this.buildingInfos = buildingInfos;
	}

}