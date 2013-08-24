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
	private String pjtname;
	private Timestamp startDate;
	private String desp;
	private String comm;
	private Set<CommunityInfo> communityInfos = new HashSet<CommunityInfo>(0);

	// Constructors

	/** default constructor */
	public ProjectInfo() {
	}

	/** minimal constructor */
	public ProjectInfo(DistrictInfo districtInfo, String pjtname,
			Timestamp startDate) {
		this.districtInfo = districtInfo;
		this.pjtname = pjtname;
		this.startDate = startDate;
	}

	/** full constructor */
	public ProjectInfo(DistrictInfo districtInfo, String pjtname,
			Timestamp startDate, String desp, String comm,
			Set<CommunityInfo> communityInfos) {
		this.districtInfo = districtInfo;
		this.pjtname = pjtname;
		this.startDate = startDate;
		this.desp = desp;
		this.comm = comm;
		this.communityInfos = communityInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "PJTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getPjtid() {
		return this.pjtid;
	}

	public void setPjtid(Long pjtid) {
		this.pjtid = pjtid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DSTID", nullable = false)
	public DistrictInfo getDistrictInfo() {
		return this.districtInfo;
	}

	public void setDistrictInfo(DistrictInfo districtInfo) {
		this.districtInfo = districtInfo;
	}

	@Column(name = "PJTNAME", nullable = false, length = 40)
	public String getPjtname() {
		return this.pjtname;
	}

	public void setPjtname(String pjtname) {
		this.pjtname = pjtname;
	}

	@Column(name = "START_DATE", nullable = false, length = 7)
	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	@Column(name = "DESP", length = 200)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectInfo")
	public Set<CommunityInfo> getCommunityInfos() {
		return this.communityInfos;
	}

	public void setCommunityInfos(Set<CommunityInfo> communityInfos) {
		this.communityInfos = communityInfos;
	}

}