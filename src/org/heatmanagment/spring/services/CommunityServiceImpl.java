package org.heatmanagment.spring.services;

import java.util.Set;

import org.heatmanagment.hibernate.domain.CommunityInfo;

public class CommunityServiceImpl implements CommunityService {

	@Override
	public void alterCommunity(Long id, String name, Long pjtId,
			String briefName, String address, String comm, String gis,
			String pic) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCommunity(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<CommunityInfo> findAllCommunity(int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
