package org.heatmanagment.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * UnitInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UNIT_INFO", schema = "HEATMGR")
public class UnitInfo implements java.io.Serializable {

	// Fields

	private Long untid;
	private MachinesetInfo machinesetInfo;
	private BuildingInfo buildingInfo;
	private String untname;
	private String gis;
	private String picaddress;
	private String comm;

	// Constructors

	/** default constructor */
	public UnitInfo() {
	}

	/** minimal constructor */
	public UnitInfo(MachinesetInfo machinesetInfo, BuildingInfo buildingInfo,
			String untname) {
		this.machinesetInfo = machinesetInfo;
		this.buildingInfo = buildingInfo;
		this.untname = untname;
	}

	/** full constructor */
	public UnitInfo(MachinesetInfo machinesetInfo, BuildingInfo buildingInfo,
			String untname, String gis, String picaddress, String comm) {
		this.machinesetInfo = machinesetInfo;
		this.buildingInfo = buildingInfo;
		this.untname = untname;
		this.gis = gis;
		this.picaddress = picaddress;
		this.comm = comm;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "UNTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getUntid() {
		return this.untid;
	}

	public void setUntid(Long untid) {
		this.untid = untid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MCHID", nullable = false)
	public MachinesetInfo getMachinesetInfo() {
		return this.machinesetInfo;
	}

	public void setMachinesetInfo(MachinesetInfo machinesetInfo) {
		this.machinesetInfo = machinesetInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLDID", nullable = false)
	public BuildingInfo getBuildingInfo() {
		return this.buildingInfo;
	}

	public void setBuildingInfo(BuildingInfo buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	@Column(name = "UNTNAME", nullable = false, length = 20)
	public String getUntname() {
		return this.untname;
	}

	public void setUntname(String untname) {
		this.untname = untname;
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

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

}