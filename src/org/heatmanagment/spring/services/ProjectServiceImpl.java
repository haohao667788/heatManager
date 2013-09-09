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
	public List<ProjectInfo> findAllProject(int start, int limit) {
		return this.dao.findAll(start, limit);
	}

	@Override
	public void saveOrUpdateProject(Long id, String name, Long ctyId,
			Long dstId, String middle, String comm) {
		ProjectInfo info = new ProjectInfo();

		CountyInfo cInfo = new CountyInfo();
		cInfo.setCtyid(ctyId);
		DistrictInfo dInfo = new DistrictInfo();
		dInfo.setDstid(dstId);

		info.setPjtid(id);
		info.setPjtname(name);
		info.setMiddle(middle);
		info.setComm(comm);
		info.setCountyInfo(cInfo);
		info.setDistrictInfo(dInfo);

		this.dao.save(info);
	}
}
