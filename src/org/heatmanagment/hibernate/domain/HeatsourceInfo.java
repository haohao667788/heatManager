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
 * HeatsourceInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HEATSOURCE_INFO", schema = "HEATMGR")
public class HeatsourceInfo implements java.io.Serializable {

	// Fields

	private Long srcid;
	private DistrictInfo districtInfo;
	private String srcname;
	private String srcaddress;
	private String heattype;
	private String desp;
	private String comm;
	private Set<BuildingInfo> buildingInfos = new HashSet<BuildingInfo>(0);
	private Set<MachinesetInfo> machinesetInfos = new HashSet<MachinesetInfo>(0);

	// Constructors

	/** default constructor */
	public HeatsourceInfo() {
	}

	/** minimal constructor */
	public HeatsourceInfo(String srcname) {
		this.srcname = srcname;
	}

	/** full constructor */
	public HeatsourceInfo(DistrictInfo districtInfo, String srcname,
			String srcaddress, String heattype, String desp, String comm,
			Set<BuildingInfo> buildingInfos, Set<MachinesetInfo> machinesetInfos) {
		this.districtInfo = districtInfo;
		this.srcname = srcname;
		this.srcaddress = srcaddress;
		this.heattype = heattype;
		this.desp = desp;
		this.comm = comm;
		this.buildingInfos = buildingInfos;
		this.machinesetInfos = machinesetInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "SRCID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getSrcid() {
		return this.srcid;
	}

	public void setSrcid(Long srcid) {
		this.srcid = srcid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DSTID")
	public DistrictInfo getDistrictInfo() {
		return this.districtInfo;
	}

	public void setDistrictInfo(DistrictInfo districtInfo) {
		this.districtInfo = districtInfo;
	}

	@Column(name = "SRCNAME", nullable = false, length = 20)
	public String getSrcname() {
		return this.srcname;
	}

	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}

	@Column(name = "SRCADDRESS", length = 200)
	public String getSrcaddress() {
		return this.srcaddress;
	}

	public void setSrcaddress(String srcaddress) {
		this.srcaddress = srcaddress;
	}

	@Column(name = "HEATTYPE", length = 20)
	public String getHeattype() {
		return this.heattype;
	}

	public void setHeattype(String heattype) {
		this.heattype = heattype;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "heatsourceInfo")
	public Set<BuildingInfo> getBuildingInfos() {
		return this.buildingInfos;
	}

	public void setBuildingInfos(Set<BuildingInfo> buildingInfos) {
		this.buildingInfos = buildingInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "heatsourceInfo")
	public Set<MachinesetInfo> getMachinesetInfos() {
		return this.machinesetInfos;
	}

	public void setMachinesetInfos(Set<MachinesetInfo> machinesetInfos) {
		this.machinesetInfos = machinesetInfos;
	}

}