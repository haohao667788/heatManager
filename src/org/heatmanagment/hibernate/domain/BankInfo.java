package org.heatmanagment.hibernate.domain;

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
import javax.persistence.UniqueConstraint;

/**
 * BankInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANK_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = {
		"BNKNUM", "ACCOUNTNUM", "CRSID" }))
public class BankInfo implements java.io.Serializable {

	// Fields

	private Long bnkid;
	private CourseInfo courseInfo;
	private String bnknum;
	private String bnkname;
	private String accountnum;
	private String desp;
	private Set<BankCertificate> bankCertificates = new HashSet<BankCertificate>(
			0);

	// Constructors

	/** default constructor */
	public BankInfo() {
	}

	/** minimal constructor */
	public BankInfo(CourseInfo courseInfo, String bnknum, String bnkname) {
		this.courseInfo = courseInfo;
		this.bnknum = bnknum;
		this.bnkname = bnkname;
	}

	/** full constructor */
	public BankInfo(CourseInfo courseInfo, String bnknum, String bnkname,
			String accountnum, String desp,
			Set<BankCertificate> bankCertificates) {
		this.courseInfo = courseInfo;
		this.bnknum = bnknum;
		this.bnkname = bnkname;
		this.accountnum = accountnum;
		this.desp = desp;
		this.bankCertificates = bankCertificates;
	}

	// Property accessors
	@SequenceGenerator(name = "BNK_ID",allocationSize = 1, sequenceName = "BNK_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BNK_ID")
	@Column(name = "BNKID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getBnkid() {
		return this.bnkid;
	}

	public void setBnkid(Long bnkid) {
		this.bnkid = bnkid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CRSID", nullable = true)
	public CourseInfo getCourseInfo() {
		return this.courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	@Column(name = "BNKNUM", nullable = true, length = 20)
	public String getBnknum() {
		return this.bnknum;
	}

	public void setBnknum(String bnknum) {
		this.bnknum = bnknum;
	}

	@Column(name = "BNKNAME", nullable = true, length = 20)
	public String getBnkname() {
		return this.bnkname;
	}

	public void setBnkname(String bnkname) {
		this.bnkname = bnkname;
	}

	@Column(name = "ACCOUNTNUM", length = 20)
	public String getAccountnum() {
		return this.accountnum;
	}

	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}

	@Column(name = "DESP", length = 2000)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bankInfo")
	public Set<BankCertificate> getBankCertificates() {
		return this.bankCertificates;
	}

	public void setBankCertificates(Set<BankCertificate> bankCertificates) {
		this.bankCertificates = bankCertificates;
	}

}