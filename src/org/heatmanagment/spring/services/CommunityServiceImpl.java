package org.heatmanagment.spring.services;

import java.util.List;
import java.util.Set;

import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.CommunityInfoDAO;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("communityService")
@SuppressWarnings("unchecked")
@Transactional
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private CommunityInfoDAO dao;

	@Override
	public void saveOrUpdateCommunity(Long id, String name, String briefName,
			String address, String comm, String gis, String pic) {
		CommunityInfo cmt = new CommunityInfo();
		cmt.setCmtid(id);
		cmt.setCmtname(name);
		cmt.setBriefname(briefName);
		cmt.setCmtaddress(address);
		cmt.setComm(comm);
		cmt.setGis(gis);
		cmt.setPicaddress(pic);
		this.dao.attachDirty(cmt);
	}

	@Override
	public void deleteCommunity(Long id) {
		CommunityInfo cmt = new CommunityInfo();
		cmt.setCmtid(id);
		this.dao.delete(cmt);
	}

	@Override
	public List<CommunityInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public List<CommunityInfo> findAll() {
		return this.dao.findAll();
	}

}
