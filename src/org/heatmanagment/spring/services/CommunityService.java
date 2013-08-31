package org.heatmanagment.spring.services;

import java.util.Set;

import org.heatmanagment.hibernate.domain.*;

public interface CommunityService {

	void alterCommunity(Long id, String name, Long pjtId, String briefName,
			String address, String comm, String gis, String pic);

	void deleteCommunity(Long id);

	Set<CommunityInfo> findAllCommunity(int start, int limit);
}
