package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.DistrictInfo;

/**
 * District information services defined here.
 * 
 * @author Desmond
 * 
 */
public interface DistrictService {

	void saveOrUpdateDistrict(Long id, String name, String comm);

	/**
	 * Delete District information according to its id.
	 * 
	 * @param id
	 */
	void deleteDistrict(Long id);

	/**
	 * Fetch District information
	 * 
	 * @param start
	 *            where to start inqury
	 * @param limit
	 *            page items limit
	 */
	List<DistrictInfo> findPage(int start, int limit);

	List<DistrictInfo> findAll();

}
