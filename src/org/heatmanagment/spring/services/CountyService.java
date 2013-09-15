package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.CountyInfo;

public interface CountyService {

	void saveOrUpdateCounty(Long id, String townname, String cityname,
			String comm);

	void deleteCounty(Long id);

	List<CountyInfo> findPage(int start, int limit);

	Long count();

	List<CountyInfo> findAll();
}
