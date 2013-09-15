package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfo;

public interface BuildingService {

	List<BuildingInfo> findPage(Long start, Long limit);

	List<BuildingInfo> findAll();

	List<BuildingInfo> findAllByCmtId(Long cmtid);

	List<BuildingInfo> findByCmtid(Long id, Long start, Long limit);

	BuildingInfo findById(Long id);

	void saveOrUpdateBuilding(Long id, String name, String address, Long cmtid,
			Long mchid, String heattype, String gis, String picaddress,
			String comm);

	void deleteBuilding(Long id);

	Long count();

	Long countByCmtId(Long cmtId);
}
