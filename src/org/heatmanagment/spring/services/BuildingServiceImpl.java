package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.BuildingInfoDAO;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfo;
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
	public List<BuildingInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public BuildingInfo findById(Long id) {
		return this.dao.findById(id);
	}

	@Override
	public void saveOrUpdateBuilding(Long id, String name, String address,
			Long cmtid, Long srcid, String heattype, String gis,
			String picaddress, String comm) {
		BuildingInfo bld = new BuildingInfo();
		bld.setBldid(id);
		bld.setBldname(name);
		bld.setBldaddress(address);
		bld.setHeattype(heattype);
		bld.setGis(gis);
		bld.setDesp(comm);
		bld.setPicaddress(picaddress);

		if (cmtid != null) {
			CommunityInfo cmt = new CommunityInfo();
			cmt.setCmtid(cmtid);
			bld.setCommunityInfo(cmt);
		}
		if (srcid != null) {
			HeatsourceInfo src = new HeatsourceInfo();
			src.setSrcid(srcid);
			bld.setHeatsourceInfo(src);
		}
		this.dao.attachDirty(bld);
	}

	@Override
	public void deleteBuilding(Long id) {
		BuildingInfo info = new BuildingInfo();
		info.setBldid(id);
		this.dao.delete(info);
	}

	@Override
	public List<CommunityInfo> inquireCmt() {

		return null;
	}

	@Override
	public List<HeatsourceInfo> inquireHeatSrc() {
		// null
		return null;
	}

	@Override
	public List<BuildingInfo> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<BuildingInfo> findByCmtid(Long id) {
		BuildingInfo bld = new BuildingInfo();
		CommunityInfo cmt = new CommunityInfo();
		cmt.setCmtid(id);
		bld.setCommunityInfo(cmt);

		return this.dao.findByExample(bld);
	}
}
