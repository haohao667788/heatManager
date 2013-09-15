package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
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

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * UsersInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USERS_INFO", schema = "HEATMGR")
@JsonAutoDetect
public class UsersInfo implements java.io.Serializable {

	// Fields

	private Long usrid;
	@JsonIgnore
	private MachinesetInfo machinesetInfo;
	@JsonIgnore
	private ProjectInfo projectInfo;
	@JsonIgnore
	private BuildingInfo buildingInfo;
	@JsonIgnore
	private UnitInfo unitInfo;
	@JsonIgnore
	private CommunityInfo communityInfo;
	private String usertype;
	private String address;
	private String usrname;
	private String phone;
	private Timestamp startdate;
	private Timestamp contractdate;
	private String contracttype;
	private String contractver;
	private String contractpic;
	private String idpic;
	private String houseidpic;
	private String housepic;
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
	private Boolean isvalid;
	private Set<DueCharge> dueCharges = new HashSet<DueCharge>(0);
	private Set<DealInfo> dealInfos = new HashSet<DealInfo>(0);
	private Set<ChargeRecord> chargeRecords = new HashSet<ChargeRecord>(0);
	private Set<UserLog> userLogs = new HashSet<UserLog>(0);

	// Constructors

	/** default constructor */
	public UsersInfo() {
	}

	/** full constructor */
	public UsersInfo(MachinesetInfo machinesetInfo, ProjectInfo projectInfo,
			BuildingInfo buildingInfo, UnitInfo unitInfo,
			CommunityInfo communityInfo, String usertype, String address,
			String usrname, String phone, Timestamp startdate,
			Timestamp contractdate, String contracttype, String contractver,
			String contractpic, String idpic, String houseidpic,
			String housepic, Double area, Double realarea, Double feearea,
			String feetype, Double feerate, Double discount, Double reducefee,
			String heatstate, Double heatbase, Double heatrate,
			String housetype, String desp, Boolean isvalid,
			Set<DueCharge> dueCharges, Set<DealInfo> dealInfos,
			Set<ChargeRecord> chargeRecords, Set<UserLog> userLogs) {
		this.machinesetInfo = machinesetInfo;
		this.projectInfo = projectInfo;
		this.buildingInfo = buildingInfo;
		this.unitInfo = unitInfo;
		this.communityInfo = communityInfo;
		this.usertype = usertype;
		this.address = address;
		this.usrname = usrname;
		this.phone = phone;
		this.startdate = startdate;
		this.contractdate = contractdate;
		this.contracttype = contracttype;
		this.contractver = contractver;
		this.contractpic = contractpic;
		this.idpic = idpic;
		this.houseidpic = houseidpic;
		this.housepic = housepic;
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
		this.isvalid = isvalid;
		this.dueCharges = dueCharges;
		this.dealInfos = dealInfos;
		this.chargeRecords = chargeRecords;
		this.userLogs = userLogs;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "USRID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getUsrid() {
		return this.usrid;
	}

	public void setUsrid(Long usrid) {
		this.usrid = usrid;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MCHID")
	public MachinesetInfo getMachinesetInfo() {
		return this.machinesetInfo;
	}

	public void setMachinesetInfo(MachinesetInfo machinesetInfo) {
		this.machinesetInfo = machinesetInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PJTID")
	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BLDID")
	public BuildingInfo getBuildingInfo() {
		return this.buildingInfo;
	}

	public void setBuildingInfo(BuildingInfo buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UNTID")
	public UnitInfo getUnitInfo() {
		return this.unitInfo;
	}

	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CMTID")
	public CommunityInfo getCommunityInfo() {
		return this.communityInfo;
	}

	public void setCommunityInfo(CommunityInfo communityInfo) {
		this.communityInfo = communityInfo;
	}

	@Column(name = "USERTYPE", length = 10)
	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "USRNAME", length = 200)
	public String getUsrname() {
		return this.usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "STARTDATE", length = 7)
	public Timestamp getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	@Column(name = "CONTRACTDATE", length = 7)
	public Timestamp getContractdate() {
		return this.contractdate;
	}

	public void setContractdate(Timestamp contractdate) {
		this.contractdate = contractdate;
	}

	@Column(name = "CONTRACTTYPE", length = 50)
	public String getContracttype() {
		return this.contracttype;
	}

	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}

	@Column(name = "CONTRACTVER", length = 10)
	public String getContractver() {
		return this.contractver;
	}

	public void setContractver(String contractver) {
		this.contractver = contractver;
	}

	@Column(name = "CONTRACTPIC", length = 200)
	public String getContractpic() {
		return this.contractpic;
	}

	public void setContractpic(String contractpic) {
		this.contractpic = contractpic;
	}

	@Column(name = "IDPIC", length = 200)
	public String getIdpic() {
		return this.idpic;
	}

	public void setIdpic(String idpic) {
		this.idpic = idpic;
	}

	@Column(name = "HOUSEIDPIC", length = 200)
	public String getHouseidpic() {
		return this.houseidpic;
	}

	public void setHouseidpic(String houseidpic) {
		this.houseidpic = houseidpic;
	}

	@Column(name = "HOUSEPIC", length = 200)
	public String getHousepic() {
		return this.housepic;
	}

	public void setHousepic(String housepic) {
		this.housepic = housepic;
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

	@Column(name = "ISVALID", precision = 1, scale = 0)
	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usersInfo")
	public Set<DueCharge> getDueCharges() {
		return this.dueCharges;
	}

	public void setDueCharges(Set<DueCharge> dueCharges) {
		this.dueCharges = dueCharges;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usersInfo")
	public Set<DealInfo> getDealInfos() {
		return this.dealInfos;
	}

	public void setDealInfos(Set<DealInfo> dealInfos) {
		this.dealInfos = dealInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usersInfo")
	public Set<ChargeRecord> getChargeRecords() {
		return this.chargeRecords;
	}

	public void setChargeRecords(Set<ChargeRecord> chargeRecords) {
		this.chargeRecords = chargeRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usersInfo")
	public Set<UserLog> getUserLogs() {
		return this.userLogs;
	}

	public void setUserLogs(Set<UserLog> userLogs) {
		this.userLogs = userLogs;
	}

}