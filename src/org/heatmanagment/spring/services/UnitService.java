package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.domain.UnitInfo;

public interface UnitService {

	void saveOrUpdate(Long id, String name, Long bldid, Long mchid, String gis,
			String picaddress);

	void delete(Long id);

	List<UnitInfo> findAll(int start, int limit);
}
