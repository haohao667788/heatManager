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

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * ClassInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CLASS_INFO", schema = "HEATMGR")
@JsonAutoDetect
public class ClassInfo implements java.io.Serializable {

	// Fields

	private Long clsid;
	private String clsname;

	@JsonProperty("desp")
	private String comm;
	private Set<HeatsourceInfo> heatsourceInfos = new HashSet<HeatsourceInfo>(0);
	private Set<MachinesetInfo> machinesetInfos = new HashSet<MachinesetInfo>(0);

	// Constructors

	/** default constructor */
	public ClassInfo() {
	}

	/** full constructor */
	public ClassInfo(String clsname, String comm,
			Set<HeatsourceInfo> heatsourceInfos,
			Set<MachinesetInfo> machinesetInfos) {
		this.clsname = clsname;
		this.comm = comm;
		this.heatsourceInfos = heatsourceInfos;
		this.machinesetInfos = machinesetInfos;
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

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "classInfo")
	public Set<HeatsourceInfo> getHeatsourceInfos() {
		return this.heatsourceInfos;
	}

	public void setHeatsourceInfos(Set<HeatsourceInfo> heatsourceInfos) {
		this.heatsourceInfos = heatsourceInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "classInfo")
	public Set<MachinesetInfo> getMachinesetInfos() {
		return this.machinesetInfos;
	}

	public void setMachinesetInfos(Set<MachinesetInfo> machinesetInfos) {
		this.machinesetInfos = machinesetInfos;
	}

}