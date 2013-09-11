package org.heatmanagment.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * BankInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANK_INFO", schema = "HEATMGR")
public class BankInfo implements java.io.Serializable {

	// Fields

	private Long bnkid;
	private CourseInfo courseInfo;
	private String bnkname;
	private Set<BankCertificate> bankCertificates = new HashSet<BankCertificate>(
			0);

	// Constructors

	/** default constructor */
	public BankInfo() {
	}

	/** minimal constructor */
	public BankInfo(String bnkname) {
		this.bnkname = bnkname;
	}

	/** full constructor */
	public BankInfo(CourseInfo courseInfo, String bnkname,
			Set<BankCertificate> bankCertificates) {
		this.courseInfo = courseInfo;
		this.bnkname = bnkname;
		this.bankCertificates = bankCertificates;
	}

	// Property accessors
	@Id
	@Column(name = "BNKID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getBnkid() {
		return this.bnkid;
	}

	public void setBnkid(Long bnkid) {
		this.bnkid = bnkid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRSID")
	public CourseInfo getCourseInfo() {
		return this.courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	@Column(name = "BNKNAME", nullable = false, length = 20)
	public String getBnkname() {
		return this.bnkname;
	}

	public void setBnkname(String bnkname) {
		this.bnkname = bnkname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bankInfo")
	public Set<BankCertificate> getBankCertificates() {
		return this.bankCertificates;
	}

	public void setBankCertificates(Set<BankCertificate> bankCertificates) {
		this.bankCertificates = bankCertificates;
	}

}