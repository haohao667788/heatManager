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
 * HeatsourceInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HEATSOURCE_INFO", schema = "HEATMGR")
public class HeatsourceInfo implements java.io.Serializable {

	// Fields

	private Long srcid;
	private DistrictInfo districtInfo;
	private String srcname;
	private String address;
	private String heattype;
	private String desp;
	private Boolean isvalid;
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
			String address, String heattype, String desp, Boolean isvalid,
			Set<MachinesetInfo> machinesetInfos) {
		this.districtInfo = districtInfo;
		this.srcname = srcname;
		this.address = address;
		this.heattype = heattype;
		this.desp = desp;
		this.isvalid = isvalid;
		this.machinesetInfos = machinesetInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "SRC_ID", allocationSize = 1, sequenceName = "SRC_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SRC_ID")
	@Column(name = "SRCID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getSrcid() {
		return this.srcid;
	}

	public void setSrcid(Long srcid) {
		this.srcid = srcid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DSTID")
	public DistrictInfo getDistrictInfo() {
		return this.districtInfo;
	}

	public void setDistrictInfo(DistrictInfo districtInfo) {
		this.districtInfo = districtInfo;
	}

	@Column(name = "SRCNAME", nullable = true, length = 200)
	public String getSrcname() {
		return this.srcname;
	}

	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "HEATTYPE", length = 20)
	public String getHeattype() {
		return this.heattype;
	}

	public void setHeattype(String heattype) {
		this.heattype = heattype;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "heatsourceInfo")
	public Set<MachinesetInfo> getMachinesetInfos() {
		return this.machinesetInfos;
	}

	public void setMachinesetInfos(Set<MachinesetInfo> machinesetInfos) {
		this.machinesetInfos = machinesetInfos;
	}

}