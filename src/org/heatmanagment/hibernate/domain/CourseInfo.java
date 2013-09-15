package org.heatmanagment.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * CourseInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COURSE_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = "CRSNUM"))
public class CourseInfo implements java.io.Serializable {

	// Fields

	private Long crsid;
	private String crsnum;
	private String crsname;
	private String desp;
	private String dealname;
	private Boolean isvalid;
	private Set<BankInfo> bankInfos = new HashSet<BankInfo>(0);

	// Constructors

	/** default constructor */
	public CourseInfo() {
	}

	/** full constructor */
	public CourseInfo(String crsnum, String crsname, String desp,
			String dealname, Boolean isvalid, Set<BankInfo> bankInfos) {
		this.crsnum = crsnum;
		this.crsname = crsname;
		this.desp = desp;
		this.dealname = dealname;
		this.isvalid = isvalid;
		this.bankInfos = bankInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "CRS_ID", allocationSize = 1, sequenceName = "CRS_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CRS_ID")
	@Column(name = "CRSID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCrsid() {
		return this.crsid;
	}

	public void setCrsid(Long crsid) {
		this.crsid = crsid;
	}

	@Column(name = "CRSNUM", length = 200)
	public String getCrsnum() {
		return this.crsnum;
	}

	public void setCrsnum(String crsnum) {
		this.crsnum = crsnum;
	}

	@Column(name = "CRSNAME", length = 200)
	public String getCrsname() {
		return this.crsname;
	}

	public void setCrsname(String crsname) {
		this.crsname = crsname;
	}

	@Column(name = "DESP", length = 2000)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	@Column(name = "DEALNAME", length = 200)
	public String getDealname() {
		return this.dealname;
	}

	public void setDealname(String dealname) {
		this.dealname = dealname;
	}

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "courseInfo")
	public Set<BankInfo> getBankInfos() {
		return this.bankInfos;
	}

	public void setBankInfos(Set<BankInfo> bankInfos) {
		this.bankInfos = bankInfos;
	}

}