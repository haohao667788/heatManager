package org.heatmanagment.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * FeeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FEE_INFO", schema = "HEATMGR")
public class FeeInfo implements java.io.Serializable {

	// Fields

	private Long feeid;
	private UsersInfo usersInfo;
	private Double area;
	private Double realarea;
	private Double feearea;
	private String feetype;
	private Double feerate;
	private Double discount;
	private Double reducefee;
	private String heatstate;
	private Double heatbase;
	private Double heatrate;
	private String housetype;
	private String desp;

	// Constructors

	/** default constructor */
	public FeeInfo() {
	}

	/** minimal constructor */
	public FeeInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	/** full constructor */
	public FeeInfo(UsersInfo usersInfo, Double area, Double realarea,
			Double feearea, String feetype, Double feerate, Double discount,
			Double reducefee, String heatstate, Double heatbase,
			Double heatrate, String housetype, String desp) {
		this.usersInfo = usersInfo;
		this.area = area;
		this.realarea = realarea;
		this.feearea = feearea;
		this.feetype = feetype;
		this.feerate = feerate;
		this.discount = discount;
		this.reducefee = reducefee;
		this.heatstate = heatstate;
		this.heatbase = heatbase;
		this.heatrate = heatrate;
		this.housetype = housetype;
		this.desp = desp;
	}

	// Property accessors
	@SequenceGenerator(name = "FEE_ID",allocationSize = 1, sequenceName = "FEE_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "FEE_ID")
	@Column(name = "FEEID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getFeeid() {
		return this.feeid;
	}

	public void setFeeid(Long feeid) {
		this.feeid = feeid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USRID", nullable = true)
	public UsersInfo getUsersInfo() {
		return this.usersInfo;
	}

	public void setUsersInfo(UsersInfo usersInfo) {
		this.usersInfo = usersInfo;
	}

	@Column(name = "AREA", precision = 8)
	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "REALAREA", precision = 8)
	public Double getRealarea() {
		return this.realarea;
	}

	public void setRealarea(Double realarea) {
		this.realarea = realarea;
	}

	@Column(name = "FEEAREA", precision = 8)
	public Double getFeearea() {
		return this.feearea;
	}

	public void setFeearea(Double feearea) {
		this.feearea = feearea;
	}

	@Column(name = "FEETYPE", length = 10)
	public String getFeetype() {
		return this.feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	@Column(name = "FEERATE", precision = 8)
	public Double getFeerate() {
		return this.feerate;
	}

	public void setFeerate(Double feerate) {
		this.feerate = feerate;
	}

	@Column(name = "DISCOUNT", precision = 8)
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "REDUCEFEE", precision = 10)
	public Double getReducefee() {
		return this.reducefee;
	}

	public void setReducefee(Double reducefee) {
		this.reducefee = reducefee;
	}

	@Column(name = "HEATSTATE", length = 10)
	public String getHeatstate() {
		return this.heatstate;
	}

	public void setHeatstate(String heatstate) {
		this.heatstate = heatstate;
	}

	@Column(name = "HEATBASE", precision = 8)
	public Double getHeatbase() {
		return this.heatbase;
	}

	public void setHeatbase(Double heatbase) {
		this.heatbase = heatbase;
	}

	@Column(name = "HEATRATE", precision = 8)
	public Double getHeatrate() {
		return this.heatrate;
	}

	public void setHeatrate(Double heatrate) {
		this.heatrate = heatrate;
	}

	@Column(name = "HOUSETYPE", length = 20)
	public String getHousetype() {
		return this.housetype;
	}

	public void setHousetype(String housetype) {
		this.housetype = housetype;
	}

	@Column(name = "DESP", length = 2000)
	public String getDesp() {
		return this.desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

}