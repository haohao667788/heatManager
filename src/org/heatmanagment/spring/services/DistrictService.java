package org.heatmanagment.spring.services;

import java.util.Set;

import org.heatmanagment.hibernate.domain.DistrictInfo;

/**
 * District information services defined here.
 * 
 * @author Desmond
 * 
 */
public interface DistrictService {

	void addDistrict(String name, String comm);

	void alterDistrict(Long id, String name, String comm);

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
	Set<DistrictInfo> findAllDistrict(int start, int limit);

}
