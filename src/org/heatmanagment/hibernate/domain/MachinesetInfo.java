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
 * MachinesetInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MACHINESET_INFO", schema = "HEATMGR")
public class MachinesetInfo implements java.io.Serializable {

	// Fields

	private Long mchid;
	private HeatsourceInfo heatsourceInfo;
	private String mchname;
	private String gis;
	private Set<UnitInfo> unitInfos = new HashSet<UnitInfo>(0);

	// Constructors

	/** default constructor */
	public MachinesetInfo() {
	}

	/** minimal constructor */
	public MachinesetInfo(HeatsourceInfo heatsourceInfo, String mchname) {
		this.heatsourceInfo = heatsourceInfo;
		this.mchname = mchname;
	}

	/** full constructor */
	public MachinesetInfo(HeatsourceInfo heatsourceInfo, String mchname,
			String gis, Set<UnitInfo> unitInfos) {
		this.heatsourceInfo = heatsourceInfo;
		this.mchname = mchname;
		this.gis = gis;
		this.unitInfos = unitInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "MCHID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getMchid() {
		return this.mchid;
	}

	public void setMchid(Long mchid) {
		this.mchid = mchid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SRCID", nullable = false)
	public HeatsourceInfo getHeatsourceInfo() {
		return this.heatsourceInfo;
	}

	public void setHeatsourceInfo(HeatsourceInfo heatsourceInfo) {
		this.heatsourceInfo = heatsourceInfo;
	}

	@Column(name = "MCHNAME", nullable = false, length = 20)
	public String getMchname() {
		return this.mchname;
	}

	public void setMchname(String mchname) {
		this.mchname = mchname;
	}

	@Column(name = "GIS", length = 20)
	public String getGis() {
		return this.gis;
	}

	public void setGis(String gis) {
		this.gis = gis;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "machinesetInfo")
	public Set<UnitInfo> getUnitInfos() {
		return this.unitInfos;
	}

	public void setUnitInfos(Set<UnitInfo> unitInfos) {
		this.unitInfos = unitInfos;
	}

}