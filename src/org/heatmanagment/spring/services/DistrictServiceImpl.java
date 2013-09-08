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

	private DistrictInfoDAO dao;

	public DistrictServiceImpl() {
		this.dao = new DistrictInfoDAO();
	}

	@Override
	public void saveOrUpdateDistrict(Long id, String name, String comm) {
		DistrictInfo temp = new DistrictInfo();
		temp.setDstid(id);
		temp.setDstname(name);
		temp.setComm(comm);
		this.dao.attachDirty(temp);
	}

	@Override
	public void deleteDistrict(Long id) {
		DistrictInfo temp = new DistrictInfo();
		temp.setDstid(id);
		this.dao.delete(temp);
	}

	@Override
	public List<DistrictInfo> findAllDistrict(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

}
