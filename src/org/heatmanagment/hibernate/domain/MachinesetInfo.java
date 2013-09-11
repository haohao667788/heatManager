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

	/**
	 * 
	 */
	private static final long serialVersionUID = 2825784415706550352L;
	private Long mchid;
	private HeatsourceInfo heatsourceInfo;
	private ClassInfo classInfo;
	private String mchname;
	private String gis;
	private Set<UsersInfo> usersInfos = new HashSet<UsersInfo>(0);
	private Set<UnitInfo> unitInfos = new HashSet<UnitInfo>(0);

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
			String mchname, String gis, Set<UsersInfo> usersInfos,
			Set<UnitInfo> unitInfos) {
		this.heatsourceInfo = heatsourceInfo;
		this.classInfo = classInfo;
		this.mchname = mchname;
		this.gis = gis;
		this.usersInfos = usersInfos;
		this.unitInfos = unitInfos;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SRCID")
	public HeatsourceInfo getHeatsourceInfo() {
		return this.heatsourceInfo;
	}

	public void setHeatsourceInfo(HeatsourceInfo heatsourceInfo) {
		this.heatsourceInfo = heatsourceInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLSID")
	public ClassInfo getClassInfo() {
		return this.classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	@Column(name = "MCHNAME", nullable = true, length = 20)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "machinesetInfo")
	public Set<UsersInfo> getUsersInfos() {
		return this.usersInfos;
	}

	public void setUsersInfos(Set<UsersInfo> usersInfos) {
		this.usersInfos = usersInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "machinesetInfo")
	public Set<UnitInfo> getUnitInfos() {
		return this.unitInfos;
	}

	public void setUnitInfos(Set<UnitInfo> unitInfos) {
		this.unitInfos = unitInfos;
	}

}