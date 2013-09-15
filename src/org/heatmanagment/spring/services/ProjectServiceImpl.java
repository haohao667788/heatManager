package org.heatmanagment.spring.services;

import java.sql.Timestamp;
import java.util.List;

import org.heatmanagment.hibernate.domain.CountyInfo;
import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.hibernate.domain.PjtStfMap;
import org.heatmanagment.hibernate.domain.PjtStfMapDAO;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.heatmanagment.hibernate.domain.ProjectInfoDAO;
import org.heatmanagment.hibernate.domain.StaffInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
@SuppressWarnings("unchecked")
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectInfoDAO dao;

	@Autowired
	private PjtStfMapDAO mapDao;

	@Override
	public void deleteProject(Long id) {
		ProjectInfo pjt = this.dao.findById(id);
		pjt.setIsvalid(false);
		this.dao.attachDirty(pjt);
	}

	@Override
	public List<ProjectInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public void saveOrUpdateProject(Long id, String name, String pjtnum,
			Long ctyId, Long dstId, String middle, String comm,
			Timestamp startDate) {
		ProjectInfo pjt = new ProjectInfo();

		if (ctyId != null) {
			CountyInfo cInfo = new CountyInfo();
			cInfo.setCtyid(ctyId);
			pjt.setCountyInfo(cInfo);
		}
		if (dstId != null) {
			DistrictInfo dInfo = new DistrictInfo();
			dInfo.setDstid(dstId);
			pjt.setDistrictInfo(dInfo);
		}

		pjt.setPjtid(id);
		pjt.setPjtname(name);
		pjt.setMiddle(middle);
		pjt.setDesp(comm);
		pjt.setStartDate(startDate);
		pjt.setPjtnum(pjtnum);
		pjt.setIsvalid(true);

		this.dao.attachDirty(pjt);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public List<ProjectInfo> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<ProjectInfo> findByDst(Long dstid) {
		return null;
	}

	@Override
	public void addMapping(Long pjtid, Long stfid, String desp) {
		PjtStfMap map = new PjtStfMap();
		map.setIsvalid(true);
		ProjectInfo pjt = new ProjectInfo();
		pjt.setPjtid(pjtid);
		StaffInfo stf = new StaffInfo();
		stf.setStfid(stfid);
		map.setDesp(desp);

		map.setProjectInfo(pjt);
		map.setStaffInfo(stf);
		this.mapDao.attachDirty(map);
	}
}
