package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.CountyInfo;
import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.heatmanagment.hibernate.domain.ProjectInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
@SuppressWarnings("unchecked")
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectInfoDAO dao;

	@Override
	public void deleteProject(Long id) {
		ProjectInfo info = new ProjectInfo();
		info.setPjtid(id);
		this.dao.delete(info);
	}

	@Override
	public List<ProjectInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public void saveOrUpdateProject(Long id, String name, Long ctyId,
			Long dstId, String middle, String comm) {
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
		pjt.setComm(comm);

		this.dao.attachDirty(pjt);
	}
}
