package org.heatmanagment.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CourseInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COURSE_INFO", schema = "HEATMGR")
public class CourseInfo implements java.io.Serializable {

	// Fields

	private Long crsid;
	private String crsname;
	private String desp;
	private String chgyear;
	private Set<BankInfo> bankInfos = new HashSet<BankInfo>(0);

	// Constructors

	/** default constructor */
	public CourseInfo() {
	}

	/** full constructor */
	public CourseInfo(String crsname, String desp, String chgyear,
			Set<BankInfo> bankInfos) {
		this.crsname = crsname;
		this.desp = desp;
		this.chgyear = chgyear;
		this.bankInfos = bankInfos;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "CRSID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCrsid() {
		return this.crsid;
	}

	public void setCrsid(Long crsid) {
		this.crsid = crsid;
	}

	@Column(name = "CRSNAME", length = 20)
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

	@Column(name = "CHGYEAR", length = 20)
	public String getChgyear() {
		return this.chgyear;
	}

	public void setChgyear(String chgyear) {
		this.chgyear = chgyear;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "courseInfo")
	public Set<BankInfo> getBankInfos() {
		return this.bankInfos;
	}

	public void setBankInfos(Set<BankInfo> bankInfos) {
		this.bankInfos = bankInfos;
	}

}