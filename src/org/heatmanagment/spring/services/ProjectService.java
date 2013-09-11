package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.hibernate.domain.ProjectInfo;

/**
 * Project related services.
 * 
 * @author Desmond
 * 
 */
public interface ProjectService {

	void deleteProject(Long id);

	List<ProjectInfo> findPage(int start, int limit);

	void saveOrUpdateProject(Long id, String name, Long ctyId, Long dstId,
			String middle, String comm);
}
