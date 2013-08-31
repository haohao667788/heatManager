package org.heatmanagment.spring.services;

import java.util.Set;

import org.heatmanagment.hibernate.domain.UnitInfo;

public interface UnitService {

	Set<UnitInfo> findAllUnit(int start, int limit);

}
