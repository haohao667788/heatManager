package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.domain.UnitInfo;
import org.heatmanagment.hibernate.domain.UnitInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("unitService")
@SuppressWarnings("unchecked")
@Transactional
public class UnitServiceImpl implements UnitService {

	@Autowired
	private UnitInfoDAO dao;

	@Override
	public List<UnitInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public UnitInfo findById(Long id) {
		return this.dao.findById(id);
	}

	@Override
	public void saveOrUpdateUnit(Long id, String name, Long bldid, Long cmtid,
			String gis, String picaddress) {
		UnitInfo unt = new UnitInfo();
		unt.setUntid(id);
		unt.setUntname(name);
		unt.setGis(gis);
		unt.setPicaddress(picaddress);
		unt.setIsvalid(true);

		if (bldid != null) {
			BuildingInfo bld = new BuildingInfo();
			bld.setBldid(bldid);
			unt.setBuildingInfo(bld);
		}
		if (cmtid != null) {
			CommunityInfo cmt = new CommunityInfo();
			cmt.setCmtid(cmtid);
			unt.setCommunityInfo(cmt);
		}
		this.dao.attachDirty(unt);
	}

	@Override
	public void deleteUnit(Long id) {
		UnitInfo unt = this.dao.findById(id);
		unt.setIsvalid(false);
		this.dao.attachDirty(unt);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public List<UnitInfo> findByBldid(Long bldid) {
		BuildingInfo bld = new BuildingInfo();
		bld.setBldid(bldid);
		UnitInfo unt = new UnitInfo();
		unt.setBuildingInfo(bld);
		return this.dao.findByExample(unt);
	}
}
