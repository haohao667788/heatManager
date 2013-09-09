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

	List<ProjectInfo> findAllProject(int start, int limit);

	/**
	 * 
	 * @param id
	 *            project id
	 * @param name
	 *            project name
	 * @param dstId
	 *            district id where the project belongs.
	 * @param middle
	 *            whether this project was purchased.
	 * @param comm
	 *            comment
	 */
	void saveOrUpdateProject(Long id, String name, Long ctyId, Long dstId,
			String middle, String comm);
}
