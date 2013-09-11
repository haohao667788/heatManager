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

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * UsersInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USERS_INFO", schema = "HEATMGR")
@JsonAutoDetect
public class UsersInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6349835540411742922L;
	private Long usrid;
	private MachinesetInfo machinesetInfo;
	private ProjectInfo projectInfo;
	private BuildingInfo buildingInfo;
	private UnitInfo unitInfo;
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
	@JsonProperty("desp")
	private String comm;
	private Set<FeeInfo> feeInfos = new HashSet<FeeInfo>(0);
	private Set<ChargeRecord> chargeRecords = new HashSet<ChargeRecord>(0);
	private Set<AccountrangeInfo> accountrangeInfos = new HashSet<AccountrangeInfo>(
			0);
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
			String housepic, String comm, Set<FeeInfo> feeInfos,
			Set<ChargeRecord> chargeRecords,
			Set<AccountrangeInfo> accountrangeInfos, Set<UserLog> userLogs) {
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
		this.comm = comm;
		this.feeInfos = feeInfos;
		this.chargeRecords = chargeRecords;
		this.accountrangeInfos = accountrangeInfos;
		this.userLogs = userLogs;
	}

	// Property accessors
	@SequenceGenerator(name = "USR_ID", allocationSize = 1, sequenceName = "USR_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USR_ID")
	@Column(name = "USRID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getUsrid() {
		return this.usrid;
	}

	public void setUsrid(Long usrid) {
		this.usrid = usrid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MCHID")
	public MachinesetInfo getMachinesetInfo() {
		return this.machinesetInfo;
	}

	public void setMachinesetInfo(MachinesetInfo machinesetInfo) {
		this.machinesetInfo = machinesetInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PJTID")
	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLDID")
	public BuildingInfo getBuildingInfo() {
		return this.buildingInfo;
	}

	public void setBuildingInfo(BuildingInfo buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNTID")
	public UnitInfo getUnitInfo() {
		return this.unitInfo;
	}

	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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

	@Column(name = "USRNAME", length = 20)
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

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersInfo")
	public Set<FeeInfo> getFeeInfos() {
		return this.feeInfos;
	}

	public void setFeeInfos(Set<FeeInfo> feeInfos) {
		this.feeInfos = feeInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersInfo")
	public Set<ChargeRecord> getChargeRecords() {
		return this.chargeRecords;
	}

	public void setChargeRecords(Set<ChargeRecord> chargeRecords) {
		this.chargeRecords = chargeRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersInfo")
	public Set<AccountrangeInfo> getAccountrangeInfos() {
		return this.accountrangeInfos;
	}

	public void setAccountrangeInfos(Set<AccountrangeInfo> accountrangeInfos) {
		this.accountrangeInfos = accountrangeInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersInfo")
	public Set<UserLog> getUserLogs() {
		return this.userLogs;
	}

	public void setUserLogs(Set<UserLog> userLogs) {
		this.userLogs = userLogs;
	}

}