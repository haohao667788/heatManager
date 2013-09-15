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
 * MachinesetInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MACHINESET_INFO", schema = "HEATMGR")
public class MachinesetInfo implements java.io.Serializable {

	// Fields

	private Long mchid;
	private HeatsourceInfo heatsourceInfo;
	private ClassInfo classInfo;
	private String mchname;
	private String gis;
	private String desp;
	private Boolean isvalid;
	private Set<BuildingInfo> buildingInfos = new HashSet<BuildingInfo>(0);
	private Set<UsersInfo> usersInfos = new HashSet<UsersInfo>(0);

	// Constructors

	/** default constructor */
	public MachinesetInfo() {
	}

	/** minimal constructor */
	public MachinesetInfo(String mchname) {
		this.mchname = mchname;
	}

	/** full constructor */
	public MachinesetInfo(HeatsourceInfo heatsourceInfo, ClassInfo classInfo,
			String mchname, String gis, String desp, Boolean isvalid,
			Set<BuildingInfo> buildingInfos, Set<UsersInfo> usersInfos) {
		this.heatsourceInfo = heatsourceInfo;
		this.classInfo = classInfo;
		this.mchname = mchname;
		this.gis = gis;
		this.desp = desp;
		this.isvalid = isvalid;
		this.buildingInfos = buildingInfos;
		this.usersInfos = usersInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "MCH_ID", allocationSize = 1, sequenceName = "MCH_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MCH_ID")
	@Column(name = "MCHID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getMchid() {
		return this.mchid;
	}

	public void setMchid(Long mchid) {
		this.mchid = mchid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SRCID")
	public HeatsourceInfo getHeatsourceInfo() {
		return this.heatsourceInfo;
	}

	public void setHeatsourceInfo(HeatsourceInfo heatsourceInfo) {
		this.heatsourceInfo = heatsourceInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLSID")
	public ClassInfo getClassInfo() {
		return this.classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	@Column(name = "MCHNAME", nullable = true, length = 200)
	public String getMchname() {
		return this.mchname;
	}

	public void setMchname(String mchname) {
		this.mchname = mchname;
	}

	@Column(name = "GIS", length = 2000)
	public String getGis() {
		return this.gis;
	}

	public void setGis(String gis) {
		this.gis = gis;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "machinesetInfo")
	public Set<BuildingInfo> getBuildingInfos() {
		return this.buildingInfos;
	}

	public void setBuildingInfos(Set<BuildingInfo> buildingInfos) {
		this.buildingInfos = buildingInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "machinesetInfo")
	public Set<UsersInfo> getUsersInfos() {
		return this.usersInfos;
	}

	public void setUsersInfos(Set<UsersInfo> usersInfos) {
		this.usersInfos = usersInfos;
	}

}