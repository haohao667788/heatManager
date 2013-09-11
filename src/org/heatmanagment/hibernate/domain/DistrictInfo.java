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
 * DistrictInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DISTRICT_INFO", schema = "HEATMGR")
@JsonAutoDetect
public class DistrictInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6965770015156663789L;
	private Long dstid;
	private String dstname;

	@JsonProperty("desp")
	private String comm;
	private Set<HeatsourceInfo> heatsourceInfos = new HashSet<HeatsourceInfo>(0);
	private Set<ProjectInfo> projectInfos = new HashSet<ProjectInfo>(0);

	// Constructors

	/** default constructor */
	public DistrictInfo() {
	}

	/** minimal constructor */
	public DistrictInfo(String dstname) {
		this.dstname = dstname;
	}

	/** full constructor */
	public DistrictInfo(String dstname, String comm,
			Set<HeatsourceInfo> heatsourceInfos, Set<ProjectInfo> projectInfos) {
		this.dstname = dstname;
		this.comm = comm;
		this.heatsourceInfos = heatsourceInfos;
		this.projectInfos = projectInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "DST_ID", allocationSize = 1, sequenceName = "DST_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DST_ID")
	@Column(name = "DSTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getDstid() {
		return this.dstid;
	}

	public void setDstid(Long dstid) {
		this.dstid = dstid;
	}

	@Column(name = "DSTNAME", nullable = false, length = 40)
	public String getDstname() {
		return this.dstname;
	}

	public void setDstname(String dstname) {
		this.dstname = dstname;
	}

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "districtInfo")
	public Set<HeatsourceInfo> getHeatsourceInfos() {
		return this.heatsourceInfos;
	}

	public void setHeatsourceInfos(Set<HeatsourceInfo> heatsourceInfos) {
		this.heatsourceInfos = heatsourceInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "districtInfo")
	public Set<ProjectInfo> getProjectInfos() {
		return this.projectInfos;
	}

	public void setProjectInfos(Set<ProjectInfo> projectInfos) {
		this.projectInfos = projectInfos;
	}

}