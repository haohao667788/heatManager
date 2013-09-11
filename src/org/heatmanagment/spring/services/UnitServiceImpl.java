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
	public void saveOrUpdateUnit(Long id, String name, Long bldid, Long cmtid,
			Long mchid, String gis, String picaddress) {
		UnitInfo unt = new UnitInfo();
		unt.setUntid(id);
		unt.setUntname(name);
		unt.setGis(gis);
		unt.setPicaddress(picaddress);

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
		if (mchid != null) {
			MachinesetInfo mch = new MachinesetInfo();
			mch.setMchid(mchid);
			unt.setMachinesetInfo(mch);
		}
		this.dao.attachDirty(unt);
	}

	@Override
	public void deleteUnit(Long id) {
		UnitInfo unt = new UnitInfo();
		unt.setUntid(id);
		this.dao.delete(unt);
	}
}
