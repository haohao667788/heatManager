package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.CountyInfo;
import org.heatmanagment.hibernate.domain.CountyInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service("countyService")
public class CountyServiceImpl implements CountyService {

	private CountyInfoDAO dao;

	public CountyServiceImpl() {
		this.dao = new CountyInfoDAO();
	}

	@Override
	@Transactional
	public void insertOrUpdateCounty(Long id, String name, String comm) {
		CountyInfo info = new CountyInfo();
		if (id == null) {
			// insert new item
			info.setCtyname(name);
			info.setComm(comm);
			this.dao.save(info);
		} else {
			// update item
			info.setCtyid(id);
			info.setCtyname(name);
			info.setComm(comm);
			this.dao.attachDirty(info);
		}
	}

	@Override
	@Transactional
	public void deleteCounty(Long id) {
		CountyInfo info = new CountyInfo();
		info.setCtyid(id);
		this.dao.delete(info);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CountyInfo> findAllCounty(int start, int limit) {

		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public CountyInfo findById(Long id) {
		return this.dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CountyInfo> findByName(String name) {
		return this.dao.findByCtyname(name);
	}
}
