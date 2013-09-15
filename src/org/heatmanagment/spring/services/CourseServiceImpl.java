package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.Config;
import org.heatmanagment.hibernate.domain.ConfigDAO;
import org.heatmanagment.hibernate.domain.CourseInfo;
import org.heatmanagment.hibernate.domain.CourseInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("courseService")
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseInfoDAO dao;

	@Autowired
	private ConfigDAO cdao;

	@Override
	public List<CourseInfo> listItems(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public void delete(Long id) {
		CourseInfo crs = this.dao.findById(id);
		crs.setIsvalid(false);
		this.dao.attachDirty(crs);
	}

	@Override
	public void saveOrUpdate(Long id, String crsnum, String name, String desp,
			String dealname) {
		CourseInfo crs = new CourseInfo();
		crs.setCrsid(id);
		crs.setCrsnum(crsnum);
		crs.setCrsname(name);
		crs.setDesp(desp);
		crs.setDealname(dealname);
		crs.setIsvalid(true);
		this.dao.attachDirty(crs);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public List<Config> listConfig() {
		List<Config> list = this.cdao.findAll();
		return list;
	}
}
