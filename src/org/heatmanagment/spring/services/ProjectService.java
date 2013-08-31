package org.heatmanagment.spring.services;

import java.util.Set;

import org.heatmanagment.hibernate.domain.ProjectInfo;

/**
 * Project related services.
 * 
 * @author Desmond
 * 
 */
public interface ProjectService {

	void deleteProject(Long id);

	Set<ProjectInfo> findAllProject(int start, int limit);

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
	void alterProject(Long id, String name, Long ctyId, Long dstId,
			String middle, String comm);
}
