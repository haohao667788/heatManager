package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.*;

public interface CommunityService {

	void saveOrUpdateCommunity(Long id, String name, String briefName,
			String address, String comm, String gis, String pic);

	void deleteCommunity(Long id);

	List<CommunityInfo> findPage(int start, int limit);

	List<CommunityInfo> findAll();
}
