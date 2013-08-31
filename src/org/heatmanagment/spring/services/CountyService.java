package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.CountyInfo;

public interface CountyService {

	void insertOrUpdateCounty(Long id, String name, String comm);

	void deleteCounty(Long id);

	List<CountyInfo> findAllCounty(int start, int limit);

	CountyInfo findById(Long id);

	List<CountyInfo> findByName(String name);

}
