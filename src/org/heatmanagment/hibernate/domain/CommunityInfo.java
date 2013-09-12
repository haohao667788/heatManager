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

/**
 * CommunityInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMMUNITY_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = "CMTNAME"))
public class CommunityInfo implements java.io.Serializable {

	// Fields

	private Long cmtid;
	private String cmtname;
	private String briefname;
	private String cmtaddress;
	private String gis;
	private String picaddress;
	private String desp;
	private Set<UsersInfo> usersInfos = new HashSet<UsersInfo>(0);
	private Set<BuildingInfo> buildingInfos = new HashSet<BuildingInfo>(0);
	private Set<UnitInfo> unitInfos = new HashSet<UnitInfo>(0);

	// Constructors

	/** default constructor */
	public CommunityInfo() {
	}

	/** full constructor */
	public CommunityInfo(String cmtname, String briefname, String cmtaddress,
			String gis, String picaddress, String desp,
			Set<UsersInfo> usersInfos, Set<BuildingInfo> buildingInfos,
			Set<UnitInfo> unitInfos) {
		this.cmtname = cmtname;
		this.briefname = briefname;
		this.cmtaddress = cmtaddress;
		this.gis = gis;
		this.picaddress = picaddress;
		this.desp = desp;
		this.usersInfos = usersInfos;
		this.buildingInfos = buildingInfos;
		this.unitInfos = unitInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "CMT_ID", allocationSize = 1, sequenceName = "CMT_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CMT_ID")
	@Column(name = "CMTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCmtid() {
		return this.cmtid;
	}

	public void setCmtid(Long cmtid) {
		this.cmtid = cmtid;
	}

	@Column(name = "CMTNAME", unique = true, length = 20)
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

	@Column(name = "CMTADDRESS", length = 200)
	public String getCmtaddress() {
		return this.cmtaddress;
	}

	public void setCmtaddress(String cmtaddress) {
		this.cmtaddress = cmtaddress;
	}

	@Column(name = "GIS", length = 2000)
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

	@Column(name = "DESP", length = 2000)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "communityInfo")
	public Set<UsersInfo> getUsersInfos() {
		return this.usersInfos;
	}

	public void setUsersInfos(Set<UsersInfo> usersInfos) {
		this.usersInfos = usersInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "communityInfo")
	public Set<BuildingInfo> getBuildingInfos() {
		return this.buildingInfos;
	}

	public void setBuildingInfos(Set<BuildingInfo> buildingInfos) {
		this.buildingInfos = buildingInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "communityInfo")
	public Set<UnitInfo> getUnitInfos() {
		return this.unitInfos;
	}

	public void setUnitInfos(Set<UnitInfo> unitInfos) {
		this.unitInfos = unitInfos;
	}

}