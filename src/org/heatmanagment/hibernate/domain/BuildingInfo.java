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
 * BuildingInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUILDING_INFO", schema = "HEATMGR")
public class BuildingInfo implements java.io.Serializable {

	// Fields

	private Long bldid;
	private HeatsourceInfo heatsourceInfo;
	private CommunityInfo communityInfo;
	private String bldname;
	private String bldaddress;
	private String heattype;
	private String gis;
	private String picaddress;
	private String desp;
	private String comm;
	private Set<UnitInfo> unitInfos = new HashSet<UnitInfo>(0);

	// Constructors

	/** default constructor */
	public BuildingInfo() {
	}

	/** minimal constructor */
	public BuildingInfo(HeatsourceInfo heatsourceInfo,
			CommunityInfo communityInfo, String bldname, String bldaddress,
			String heattype) {
		this.heatsourceInfo = heatsourceInfo;
		this.communityInfo = communityInfo;
		this.bldname = bldname;
		this.bldaddress = bldaddress;
		this.heattype = heattype;
	}

	/** full constructor */
	public BuildingInfo(HeatsourceInfo heatsourceInfo,
			CommunityInfo communityInfo, String bldname, String bldaddress,
			String heattype, String gis, String picaddress, String desp,
			String comm, Set<UnitInfo> unitInfos) {
		this.heatsourceInfo = heatsourceInfo;
		this.communityInfo = communityInfo;
		this.bldname = bldname;
		this.bldaddress = bldaddress;
		this.heattype = heattype;
		this.gis = gis;
		this.picaddress = picaddress;
		this.desp = desp;
		this.comm = comm;
		this.unitInfos = unitInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "BLDID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getBldid() {
		return this.bldid;
	}

	public void setBldid(Long bldid) {
		this.bldid = bldid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SRCID", nullable = false)
	public HeatsourceInfo getHeatsourceInfo() {
		return this.heatsourceInfo;
	}

	public void setHeatsourceInfo(HeatsourceInfo heatsourceInfo) {
		this.heatsourceInfo = heatsourceInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CMTID", nullable = false)
	public CommunityInfo getCommunityInfo() {
		return this.communityInfo;
	}

	public void setCommunityInfo(CommunityInfo communityInfo) {
		this.communityInfo = communityInfo;
	}

	@Column(name = "BLDNAME", nullable = false, length = 20)
	public String getBldname() {
		return this.bldname;
	}

	public void setBldname(String bldname) {
		this.bldname = bldname;
	}

	@Column(name = "BLDADDRESS", nullable = false, length = 200)
	public String getBldaddress() {
		return this.bldaddress;
	}

	public void setBldaddress(String bldaddress) {
		this.bldaddress = bldaddress;
	}

	@Column(name = "HEATTYPE", nullable = false, length = 10)
	public String getHeattype() {
		return this.heattype;
	}

	public void setHeattype(String heattype) {
		this.heattype = heattype;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "buildingInfo")
	public Set<UnitInfo> getUnitInfos() {
		return this.unitInfos;
	}

	public void setUnitInfos(Set<UnitInfo> unitInfos) {
		this.unitInfos = unitInfos;
	}

}