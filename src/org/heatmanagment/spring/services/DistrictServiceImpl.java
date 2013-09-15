package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.hibernate.domain.DistrictInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Service("districtService")
@Transactional
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictInfoDAO dao;

	@Override
	public void saveOrUpdateDistrict(Long id, String name, String comm) {
		DistrictInfo temp = new DistrictInfo();
		temp.setDstid(id);
		temp.setDstname(name);
		temp.setDesp(comm);
		temp.setIsvalid(true);
		this.dao.attachDirty(temp);
	}

	@Override
	public void deleteDistrict(Long id) {
		DistrictInfo dst = this.dao.findById(id);
		dst.setIsvalid(false);
		this.dao.attachDirty(dst);
	}

	@Override
	public List<DistrictInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public List<DistrictInfo> findAll() {
		return this.dao.findAll();
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

}
