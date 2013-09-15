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
 * Config entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONFIG", schema = "HEATMGR")
public class Config implements java.io.Serializable {

	// Fields

	private Long cfgid;
	private String dealname;
	private Timestamp startdate;
	private Timestamp enddate;

	// Constructors

	/** default constructor */
	public Config() {
	}

	/** full constructor */
	public Config(String dealname, Timestamp startdate, Timestamp enddate) {
		this.dealname = dealname;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	// Property accessors
	@SequenceGenerator(name = "CFG_ID", allocationSize = 1, sequenceName = "CFG_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CFG_ID")
	@Column(name = "CFGID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCfgid() {
		return this.cfgid;
	}

	public void setCfgid(Long cfgid) {
		this.cfgid = cfgid;
	}

	@Column(name = "DEALNAME", length = 200)
	public String getDealname() {
		return this.dealname;
	}

	public void setDealname(String dealname) {
		this.dealname = dealname;
	}

	@Column(name = "STARTDATE", length = 7)
	public Timestamp getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	@Column(name = "ENDDATE", length = 7)
	public Timestamp getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

}