package org.heatmanagment.spring.entity;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.heatmanagment.hibernate.util.DateConverter;

@JsonAutoDetect
public class ProjectOut {

	private Long pjtid;
	private Long dstid;
	private String dstname;
	private Long ctyid;
	private String ctyname;
	private String pjtnum;
	private String pjtname;
	private String middle;
	private String departmentname;
	private String startDate;
	private String desp;

	public Long getPjtid() {
		return pjtid;
	}

	public void setPjtid(Long pjtid) {
		this.pjtid = pjtid;
	}

	public Long getDstid() {
		return dstid;
	}

	public void setDstid(Long dstid) {
		this.dstid = dstid;
	}

	public String getDstname() {
		return dstname;
	}

	public void setDstname(String dstname) {
		this.dstname = dstname;
	}

	public Long getCtyid() {
		return ctyid;
	}

	public void setCtyid(Long ctyid) {
		this.ctyid = ctyid;
	}

	public String getCtyname() {
		return ctyname;
	}

	public void setCtyname(String ctyname) {
		this.ctyname = ctyname;
	}

	public String getPjtnum() {
		return pjtnum;
	}

	public void setPjtnum(String pjtnum) {
		this.pjtnum = pjtnum;
	}

	public String getPjtname() {
		return pjtname;
	}

	public void setPjtname(String pjtname) {
		this.pjtname = pjtname;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp start) {
		this.startDate = DateConverter.convert(start);
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

}
