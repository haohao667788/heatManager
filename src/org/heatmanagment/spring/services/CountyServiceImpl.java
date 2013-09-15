package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.CountyInfo;
import org.heatmanagment.hibernate.domain.CountyInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("countyService")
@SuppressWarnings("unchecked")
@Transactional
public class CountyServiceImpl implements CountyService {

	@Autowired
	private CountyInfoDAO dao;

	@Override
	public void saveOrUpdateCounty(Long id, String townname, String cityname,
			String comm) {
		CountyInfo cty = new CountyInfo();
		cty.setCtyid(id);
		cty.setTownname(townname);
		cty.setCityname(cityname);
		cty.setDesp(comm);
		cty.setIsvalid(true);
		this.dao.attachDirty(cty);
	}

	@Override
	public void deleteCounty(Long id) {
		CountyInfo cty = this.dao.findById(id);
		cty.setIsvalid(false);
		this.dao.attachDirty(cty);
	}

	@Override
	public List<CountyInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public List<CountyInfo> findAll() {
		return this.dao.findAll();
	}
}
