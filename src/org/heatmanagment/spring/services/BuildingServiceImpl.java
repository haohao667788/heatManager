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
	public List<BuildingInfo> findAll(int start, int limit) {
		return this.dao.findAll(start, limit);
	}

	@Override
	public void saveOrUpdate(Long id, String name, String address, Long cmtid,
			Long srcid, String heattype, String gis, String picaddress,
			String comm) {
		BuildingInfo bld = new BuildingInfo();
		bld.setBldid(id);
		bld.setBldname(name);
		bld.setBldaddress(address);
		bld.setHeattype(heattype);
		bld.setGis(gis);
		bld.setComm(comm);
		bld.setPicaddress(picaddress);

		CommunityInfo cmt = new CommunityInfo();
		cmt.setCmtid(cmtid);

		HeatsourceInfo src = new HeatsourceInfo();
		src.setSrcid(srcid);

		bld.setCommunityInfo(cmt);
		bld.setHeatsourceInfo(src);
		this.dao.attachDirty(bld);
	}

	@Override
	public void delete(Long id) {
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
		// TODO Auto-generated method stub
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
