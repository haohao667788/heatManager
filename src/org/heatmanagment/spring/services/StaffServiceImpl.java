package org.heatmanagment.spring.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.heatmanagment.hibernate.domain.PjtStfMap;
import org.heatmanagment.hibernate.domain.PjtStfMapDAO;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.heatmanagment.hibernate.domain.StaffInfo;
import org.heatmanagment.hibernate.domain.StaffInfoDAO;
import org.heatmanagment.spring.entity.StaffOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("staffService")
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffInfoDAO dao;

	@Autowired
	private PjtStfMapDAO mapDao;

	@Override
	public void saveOrUpdateCounty(Long id, String stfname, String stfnum,
			Timestamp startdate, String phone, String loginname, String pwd,
			String department, Long verifytype) {
		StaffInfo stf = new StaffInfo();
		stf.setIsvalid(true);
		stf.setStfid(id);
		stf.setStfname(stfname);
		stf.setStfnumber(stfnum);
		stf.setStartdate(startdate);
		stf.setPhone(phone);
		stf.setLoginname(loginname);
		stf.setPwd(pwd);
		stf.setDepartment(department);
		stf.setVerifytype(verifytype);
		this.dao.attachDirty(stf);
	}

	@Override
	public void delete(Long id) {
		StaffInfo sft = this.dao.findById(id);
		sft.setIsvalid(false);
		this.dao.attachDirty(sft);
	}

	@Override
	public List<StaffInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public List<StaffInfo> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<StaffOut> findByPjtid(Long pjtid) {
		PjtStfMap map = new PjtStfMap();
		ProjectInfo pjt = new ProjectInfo();
		pjt.setPjtid(pjtid);
		map.setProjectInfo(pjt);
		List<PjtStfMap> out = this.mapDao.findByExample(map);
		List<StaffOut> data = new ArrayList<StaffOut>();
		for (PjtStfMap i : out) {
			StaffOut temp = new StaffOut();
			StaffInfo s = i.getStaffInfo();
			temp.setStfid(s.getStfid());
			temp.setStfnumber(s.getStfnumber());
			temp.setStfname(s.getStfname());
			data.add(temp);
		}
		return data;
	}
}
