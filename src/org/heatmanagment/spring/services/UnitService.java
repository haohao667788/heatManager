package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.domain.UnitInfo;

public interface UnitService {

	void saveOrUpdateUnit(Long id, String name, Long bldid, Long cmtid,
			String gis, String picaddress);

	void deleteUnit(Long id);

	List<UnitInfo> findPage(int start, int limit);

	UnitInfo findById(Long id);

	List<UnitInfo> findByBldid(Long bldid);

	Long count();
}
