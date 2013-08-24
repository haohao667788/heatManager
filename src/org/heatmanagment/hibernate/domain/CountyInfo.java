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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * CountyInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COUNTY_INFO", schema = "HEATMGR")
public class CountyInfo implements java.io.Serializable {

	// Fields

	private Long ctyid;
	private String ctyname;
	private String comm;
	private Set<DistrictInfo> districtInfos = new HashSet<DistrictInfo>(0);

	// Constructors

	/** default constructor */
	public CountyInfo() {
	}

	/** minimal constructor */
	public CountyInfo(String ctyname) {
		this.ctyname = ctyname;
	}

	/** full constructor */
	public CountyInfo(String ctyname, String comm,
			Set<DistrictInfo> districtInfos) {
		this.ctyname = ctyname;
		this.comm = comm;
		this.districtInfos = districtInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "CTYID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCtyid() {
		return this.ctyid;
	}

	public void setCtyid(Long ctyid) {
		this.ctyid = ctyid;
	}

	@Column(name = "CTYNAME", nullable = false, length = 20)
	public String getCtyname() {
		return this.ctyname;
	}

	public void setCtyname(String ctyname) {
		this.ctyname = ctyname;
	}

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "countyInfo")
	public Set<DistrictInfo> getDistrictInfos() {
		return this.districtInfos;
	}

	public void setDistrictInfos(Set<DistrictInfo> districtInfos) {
		this.districtInfos = districtInfos;
	}

}