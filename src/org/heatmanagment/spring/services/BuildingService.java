package org.heatmanagment.spring.services;

import java.util.Set;

import org.heatmanagment.hibernate.domain.BuildingInfo;

public interface BuildingService {

	Set<BuildingInfo> findAllBuilding(int start, int limit);

}
