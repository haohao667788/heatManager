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

/**
 * ClassInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CLASS_INFO", schema = "HEATMGR")
public class ClassInfo implements java.io.Serializable {

	// Fields

	private Long clsid;
	private String clsname;
	private String desp;
	private Set<MachinesetInfo> machinesetInfos = new HashSet<MachinesetInfo>(0);
	private Set<HeatsourceInfo> heatsourceInfos = new HashSet<HeatsourceInfo>(0);

	// Constructors

	/** default constructor */
	public ClassInfo() {
	}

	/** full constructor */
	public ClassInfo(String clsname, String desp,
			Set<MachinesetInfo> machinesetInfos,
			Set<HeatsourceInfo> heatsourceInfos) {
		this.clsname = clsname;
		this.desp = desp;
		this.machinesetInfos = machinesetInfos;
		this.heatsourceInfos = heatsourceInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "CLS_ID", allocationSize = 1, sequenceName = "CLS_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CLS_ID")
	@Column(name = "CLSID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getClsid() {
		return this.clsid;
	}

	public void setClsid(Long clsid) {
		this.clsid = clsid;
	}

	@Column(name = "CLSNAME", length = 20)
	public String getClsname() {
		return this.clsname;
	}

	public void setClsname(String clsname) {
		this.clsname = clsname;
	}

	@Column(name = "DESP", length = 2000)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "classInfo")
	public Set<MachinesetInfo> getMachinesetInfos() {
		return this.machinesetInfos;
	}

	public void setMachinesetInfos(Set<MachinesetInfo> machinesetInfos) {
		this.machinesetInfos = machinesetInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "classInfo")
	public Set<HeatsourceInfo> getHeatsourceInfos() {
		return this.heatsourceInfos;
	}

	public void setHeatsourceInfos(Set<HeatsourceInfo> heatsourceInfos) {
		this.heatsourceInfos = heatsourceInfos;
	}

}