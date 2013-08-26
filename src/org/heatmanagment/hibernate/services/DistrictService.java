package org.heatmanagment.hibernate.services;

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

	void alterDistrict();

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
	Set<DistrictInfo> inquireDistrict(int start, int limit);

}
