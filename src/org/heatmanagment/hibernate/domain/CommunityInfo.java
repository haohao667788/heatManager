package org.heatmanagment.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * CommunityInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMMUNITY_INFO", schema = "HEATMGR", uniqueConstraints = @UniqueConstraint(columnNames = "CMTNAME"))
public class CommunityInfo implements java.io.Serializable {

	// Fields

	private Long cmtid;
	private String cmtname;
	private String briefname;
	private String cmtaddress;
	private String gis;
	private String picaddress;
	private String comm;
	private Set<UsersInfo> usersInfos = new HashSet<UsersInfo>(0);

	// Constructors

	/** default constructor */
	public CommunityInfo() {
	}

	/** full constructor */
	public CommunityInfo(String cmtname, String briefname, String cmtaddress,
			String gis, String picaddress, String comm,
			Set<UsersInfo> usersInfos) {
		this.cmtname = cmtname;
		this.briefname = briefname;
		this.cmtaddress = cmtaddress;
		this.gis = gis;
		this.picaddress = picaddress;
		this.comm = comm;
		this.usersInfos = usersInfos;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "CMTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCmtid() {
		return this.cmtid;
	}

	public void setCmtid(Long cmtid) {
		this.cmtid = cmtid;
	}

	@Column(name = "CMTNAME", unique = true, length = 20)
	public String getCmtname() {
		return this.cmtname;
	}

	public void setCmtname(String cmtname) {
		this.cmtname = cmtname;
	}

	@Column(name = "BRIEFNAME", length = 10)
	public String getBriefname() {
		return this.briefname;
	}

	public void setBriefname(String briefname) {
		this.briefname = briefname;
	}

	@Column(name = "CMTADDRESS", length = 200)
	public String getCmtaddress() {
		return this.cmtaddress;
	}

	public void setCmtaddress(String cmtaddress) {
		this.cmtaddress = cmtaddress;
	}

	@Column(name = "GIS", length = 2000)
	public String getGis() {
		return this.gis;
	}

	public void setGis(String gis) {
		this.gis = gis;
	}

	@Column(name = "PICADDRESS", length = 100)
	public String getPicaddress() {
		return this.picaddress;
	}

	public void setPicaddress(String picaddress) {
		this.picaddress = picaddress;
	}

	@Column(name = "COMM", length = 2000)
	public String getComm() {
		return this.comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "communityInfo")
	public Set<UsersInfo> getUsersInfos() {
		return this.usersInfos;
	}

	public void setUsersInfos(Set<UsersInfo> usersInfos) {
		this.usersInfos = usersInfos;
	}

}