package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.BuildingInfoDAO;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("buildingService")
@SuppressWarnings("unchecked")
@Transactional
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingInfoDAO dao;

	@Override
	public List<BuildingInfo> findPage(Long start, Long limit) {
		return this.dao.findPage(start.intValue(), limit.intValue());
	}

	@Override
	public BuildingInfo findById(Long id) {
		return this.dao.findById(id);
	}

	@Override
	public void saveOrUpdateBuilding(Long id, String name, String address,
			Long cmtid, Long mchid, String heattype, String gis,
			String picaddress, String comm) {
		BuildingInfo bld = new BuildingInfo();
		bld.setBldid(id);
		bld.setBldname(name);
		bld.setAddress(address);
		bld.setHeattype(heattype);
		bld.setGis(gis);
		bld.setDesp(comm);
		bld.setPicaddress(picaddress);
		bld.setIsvalid(true);

		if (cmtid != null) {
			CommunityInfo cmt = new CommunityInfo();
			cmt.setCmtid(cmtid);
			bld.setCommunityInfo(cmt);
		}
		if (mchid != null) {
			MachinesetInfo mch = new MachinesetInfo();
			mch.setMchid(mchid);
			bld.setMachinesetInfo(mch);
		}
		this.dao.attachDirty(bld);
	}

	@Override
	public void deleteBuilding(Long id) {
		BuildingInfo bld = this.dao.findById(id);
		bld.setIsvalid(false);
		this.dao.attachDirty(bld);
	}

	@Override
	public List<BuildingInfo> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<BuildingInfo> findByCmtid(Long id, Long start, Long limit) {
		return this.dao.findPageByCmtid(id, start, limit);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public Long countByCmtId(Long cmtId) {
		return this.dao.countByCmt(cmtId);
	}

	@Override
	public List<BuildingInfo> findAllByCmtId(Long cmtid) {
		return this.dao.findAllByCmtId(cmtid);
	}
}
