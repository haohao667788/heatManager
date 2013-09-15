package org.heatmanagment.spring.services;

import java.sql.Timestamp;
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

	List<ProjectInfo> findAll();

	List<ProjectInfo> findByDst(Long dstid);

	void saveOrUpdateProject(Long id, String name, String pjtnum, Long ctyId,
			Long dstId, String middle, String comm, Timestamp startDate);

	void addMapping(Long pjtid, Long stfid, String desp);

	Long count();
}
