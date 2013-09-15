package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("heatsourceService")
@SuppressWarnings("unchecked")
@Transactional
public class HeatsourceServiceImpl implements HeatsourceService {

	@Autowired
	private HeatsourceInfoDAO dao;

	@Override
	public List<HeatsourceInfo> listAll() {
		return this.dao.findAll();
	}

	@Override
	public void delete(Long id) {
		HeatsourceInfo src = this.dao.findById(id);
		src.setIsvalid(false);
		this.dao.attachDirty(src);
	}

	@Override
	public void saveOrUpdate(Long id, String name, String address, String type,
			String desp, Long dstid) {
		HeatsourceInfo src = new HeatsourceInfo();
		src.setSrcid(id);
		src.setSrcname(name);
		src.setAddress(address);
		src.setHeattype(type);
		src.setDesp(desp);
		src.setIsvalid(true);

		if (dstid != null) {
			DistrictInfo dst = new DistrictInfo();
			dst.setDstid(dstid);
			src.setDistrictInfo(dst);
		}
		this.dao.attachDirty(src);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public List<HeatsourceInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}
}
