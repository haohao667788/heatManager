package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfo;

public interface BuildingService {

	List<BuildingInfo> findPage(int start, int limit);

	List<BuildingInfo> findAll();

	List<BuildingInfo> findByCmtid(Long id);

	BuildingInfo findById(Long id);

	void saveOrUpdateBuilding(Long id, String name, String address, Long cmtid,
			Long srcid, String heattype, String gis, String picaddress,
			String comm);

	void deleteBuilding(Long id);

	List<CommunityInfo> inquireCmt();

	List<HeatsourceInfo> inquireHeatSrc();
}
