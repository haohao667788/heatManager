package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
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
 * ProjectInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROJECT_INFO", schema = "HEATMGR")
public class ProjectInfo implements java.io.Serializable {

	// Fields

	private Long pjtid;
	private DistrictInfo districtInfo;
	private CountyInfo countyInfo;
	private String pjtname;
	private String middle;
	private Timestamp startDate;
	private String comm;
	private Set<UsersInfo> usersInfos = new HashSet<UsersInfo>(0);

	// Constructors

	/** default constructor */
	public ProjectInfo() {
	}

	/** minimal constructor */
	public ProjectInfo(String pjtname) {
		this.pjtname = pjtname;
	}

	/** full constructor */
	public ProjectInfo(DistrictInfo districtInfo, CountyInfo countyInfo,
			String pjtname, String middle, Timestamp startDate, String comm,
			Set<UsersInfo> usersInfos) {
		this.districtInfo = districtInfo;
		this.countyInfo = countyInfo;
		this.pjtname = pjtname;
		this.middle = middle;
		this.startDate = startDate;
		this.comm = comm;
		this.usersInfos = usersInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "PJT_ID", allocationSize = 1, sequenceName = "PJT_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PJT_ID")
	@Column(name = "PJTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getPjtid() {
		return this.pjtid;
	}

	public void setPjtid(Long pjtid) {
		this.pjtid = pjtid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DSTID")
	public DistrictInfo getDistrictInfo() {
		return this.districtInfo;
	}

	public void setDistrictInfo(DistrictInfo districtInfo) {
		this.districtInfo = districtInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CTYID")
	public CountyInfo getCountyInfo() {
		return this.countyInfo;
	}

	public void setCountyInfo(CountyInfo countyInfo) {
		this.countyInfo = countyInfo;
	}

	@Column(name = "PJTNAME", nullable = false, length = 40)
	public String getPjtname() {
		return this.pjtname;
	}

	public void setPjtname(String pjtname) {
		this.pjtname = pjtname;
	}

	@Column(name = "MIDDLE", length = 4)
	public String getMiddle() {
		return this.middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	@Column(name = "START_DATE", length = 7)
	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectInfo")
	public Set<UsersInfo> getUsersInfos() {
		return this.usersInfos;
	}

	public void setUsersInfos(Set<UsersInfo> usersInfos) {
		this.usersInfos = usersInfos;
	}

}