package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * DealGenerator entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEAL_GENERATOR", schema = "HEATMGR")
public class DealGenerator implements java.io.Serializable {

	// Fields

	private Long genid;
	private String dealname;
	private Timestamp dealdate;

	// Constructors

	/** default constructor */
	public DealGenerator() {
	}

	/** full constructor */
	public DealGenerator(String dealname, Timestamp dealdate) {
		this.dealname = dealname;
		this.dealdate = dealdate;
	}

	// Property accessors
	@SequenceGenerator(name = "DEAL_GEN",allocationSize = 1, sequenceName = "DEAL_GEN")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DEAL_GEN")
	@Column(name = "GENID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getGenid() {
		return this.genid;
	}

	public void setGenid(Long genid) {
		this.genid = genid;
	}

	@Column(name = "DEALNAME", nullable = true, length = 20)
	public String getDealname() {
		return this.dealname;
	}

	public void setDealname(String dealname) {
		this.dealname = dealname;
	}

	@Column(name = "DEALDATE", nullable = true, length = 7)
	public Timestamp getDealdate() {
		return this.dealdate;
	}

	public void setDealdate(Timestamp dealdate) {
		this.dealdate = dealdate;
	}

}