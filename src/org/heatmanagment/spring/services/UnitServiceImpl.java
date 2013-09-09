package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
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
	public List<UnitInfo> findAll(int start, int limit) {
		return this.dao.findAll(start, limit);
	}

	@Override
	public void saveOrUpdate(Long id, String name, Long bldid, Long mchid,
			String gis, String picaddress) {
		UnitInfo unt = new UnitInfo();
		unt.setUntid(id);
		unt.setUntname(name);
		unt.setGis(gis);
		unt.setPicaddress(picaddress);

		BuildingInfo bld = new BuildingInfo();
		bld.setBldid(bldid);

		MachinesetInfo mch = new MachinesetInfo();
		mch.setMchid(mchid);

		unt.setMachinesetInfo(mch);
		unt.setBuildingInfo(bld);

		this.dao.attachDirty(unt);
	}

	@Override
	public void delete(Long id) {
		UnitInfo unt = new UnitInfo();
		unt.setUntid(id);
		this.dao.delete(unt);
	}
}
