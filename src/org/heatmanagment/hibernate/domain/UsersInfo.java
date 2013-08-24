package org.heatmanagment.hibernate.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * UsersInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USERS_INFO", schema = "HEATMGR")
public class UsersInfo implements java.io.Serializable {

	// Fields

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
	private String comm;

	// Constructors

	/** default constructor */
	public UsersInfo() {
	}

	/** minimal constructor */
	public UsersInfo(BuildingInfo buildingInfo, UnitInfo unitInfo,
			CommunityInfo communityInfo) {
		this.buildingInfo = buildingInfo;
		this.unitInfo = unitInfo;
		this.communityInfo = communityInfo;
	}

	/** full constructor */
	public UsersInfo(MachinesetInfo machinesetInfo, ProjectInfo projectInfo,
			BuildingInfo buildingInfo, UnitInfo unitInfo,
			CommunityInfo communityInfo, String usertype, String address,
			String usrname, String phone, Timestamp startdate,
			Timestamp contractdate, String contracttype, String contractver,
			String contractpic, String idpic, String houseidpic,
			String housepic, String comm) {
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
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
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
	@JoinColumn(name = "BLDID", nullable = false)
	public BuildingInfo getBuildingInfo() {
		return this.buildingInfo;
	}

	public void setBuildingInfo(BuildingInfo buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UNTID", nullable = false)
	public UnitInfo getUnitInfo() {
		return this.unitInfo;
	}

	public void setUnitInfo(UnitInfo unitInfo) {
		this.unitInfo = unitInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CMTID", nullable = false)
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

}