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
import javax.persistence.UniqueConstraint;

/**
 * UnitInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UNIT_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = {
		"CMTNAME", "BLDNAME", "UNTNAME" }))
public class UnitInfo implements java.io.Serializable {

	// Fields

	private Long untid;
	private MachinesetInfo machinesetInfo;
	private String cmtname;
	private String bldname;
	private String untname;
	private String gis;
	private String picaddress;
	private String comm;
	private Set<UsersInfo> usersInfos = new HashSet<UsersInfo>(0);

	// Constructors

	/** default constructor */
	public UnitInfo() {
	}

	/** full constructor */
	public UnitInfo(MachinesetInfo machinesetInfo, String cmtname,
			String bldname, String untname, String gis, String picaddress,
			String comm, Set<UsersInfo> usersInfos) {
		this.machinesetInfo = machinesetInfo;
		this.cmtname = cmtname;
		this.bldname = bldname;
		this.untname = untname;
		this.gis = gis;
		this.picaddress = picaddress;
		this.comm = comm;
		this.usersInfos = usersInfos;
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
	@JoinColumn(name = "MCHID")
	public MachinesetInfo getMachinesetInfo() {
		return this.machinesetInfo;
	}

	public void setMachinesetInfo(MachinesetInfo machinesetInfo) {
		this.machinesetInfo = machinesetInfo;
	}

	@Column(name = "CMTNAME", length = 20)
	public String getCmtname() {
		return this.cmtname;
	}

	public void setCmtname(String cmtname) {
		this.cmtname = cmtname;
	}

	@Column(name = "BLDNAME", length = 20)
	public String getBldname() {
		return this.bldname;
	}

	public void setBldname(String bldname) {
		this.bldname = bldname;
	}

	@Column(name = "UNTNAME", length = 20)
	public String getUntname() {
		return this.untname;
	}

	public void setUntname(String untname) {
		this.untname = untname;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unitInfo")
	public Set<UsersInfo> getUsersInfos() {
		return this.usersInfos;
	}

	public void setUsersInfos(Set<UsersInfo> usersInfos) {
		this.usersInfos = usersInfos;
	}

}