package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.HeatsourceInfo;

public interface HeatsourceService {

	List<HeatsourceInfo> listAll();

	List<HeatsourceInfo> findPage(int start, int limit);

	void delete(Long id);

	void saveOrUpdate(Long id, String name, String address, String type,
			String desp, Long dstid);

	Long count();
}
