package org.heatmanagment.hibernate.domain;

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
 * BuildingInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUILDING_INFO", schema = "HEATMGR")
public class BuildingInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8123468649073859264L;
	private Long bldid;
	private HeatsourceInfo heatsourceInfo;
	private CommunityInfo communityInfo;
	private String bldname;
	private String bldaddress;
	private String heattype;
	private String gis;
	private String picaddress;
	private String comm;
	private Set<UsersInfo> usersInfos = new HashSet<UsersInfo>(0);
	private Set<UnitInfo> unitInfos = new HashSet<UnitInfo>(0);

	// Constructors

	/** default constructor */
	public BuildingInfo() {
	}

	/** full constructor */
	public BuildingInfo(HeatsourceInfo heatsourceInfo,
			CommunityInfo communityInfo, String bldname, String bldaddress,
			String heattype, String gis, String picaddress, String comm,
			Set<UsersInfo> usersInfos, Set<UnitInfo> unitInfos) {
		this.heatsourceInfo = heatsourceInfo;
		this.communityInfo = communityInfo;
		this.bldname = bldname;
		this.bldaddress = bldaddress;
		this.heattype = heattype;
		this.gis = gis;
		this.picaddress = picaddress;
		this.comm = comm;
		this.usersInfos = usersInfos;
		this.unitInfos = unitInfos;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "BLD_ID", allocationSize = 1, sequenceName = "BLD_ID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BLD_ID")
	@Column(name = "BLDID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getBldid() {
		return this.bldid;
	}

	public void setBldid(Long bldid) {
		this.bldid = bldid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SRCID")
	public HeatsourceInfo getHeatsourceInfo() {
		return this.heatsourceInfo;
	}

	public void setHeatsourceInfo(HeatsourceInfo heatsourceInfo) {
		this.heatsourceInfo = heatsourceInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CMTID")
	public CommunityInfo getCommunityInfo() {
		return this.communityInfo;
	}

	public void setCommunityInfo(CommunityInfo communityInfo) {
		this.communityInfo = communityInfo;
	}

	@Column(name = "BLDNAME", length = 20)
	public String getBldname() {
		return this.bldname;
	}

	public void setBldname(String bldname) {
		this.bldname = bldname;
	}

	@Column(name = "BLDADDRESS", length = 200)
	public String getBldaddress() {
		return this.bldaddress;
	}

	public void setBldaddress(String bldaddress) {
		this.bldaddress = bldaddress;
	}

	@Column(name = "HEATTYPE", length = 10)
	public String getHeattype() {
		return this.heattype;
	}

	public void setHeattype(String heattype) {
		this.heattype = heattype;
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

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "buildingInfo")
	public Set<UsersInfo> getUsersInfos() {
		return this.usersInfos;
	}

	public void setUsersInfos(Set<UsersInfo> usersInfos) {
		this.usersInfos = usersInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "buildingInfo")
	public Set<UnitInfo> getUnitInfos() {
		return this.unitInfos;
	}

	public void setUnitInfos(Set<UnitInfo> unitInfos) {
		this.unitInfos = unitInfos;
	}

}