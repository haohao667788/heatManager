package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.MachinesetInfo;

public interface MachineSetService {

	List<MachinesetInfo> listItems(int start, int limit);

	void saveOrUpdate(Long mId, String name, String gis, String desp,
			Long scrId, Long clsId);

	void delete(Long mId);

	List<MachinesetInfo> findAll();

	List<MachinesetInfo> findPage(int start, int limit);

	List<Object> findMchByBld(Long bldid);

	Long count();
}
