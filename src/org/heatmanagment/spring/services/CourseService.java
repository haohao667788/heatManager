package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.Config;
import org.heatmanagment.hibernate.domain.CourseInfo;

public interface CourseService {

	List<CourseInfo> listItems(int start, int limit);

	void delete(Long id);

	void saveOrUpdate(Long id, String crsnum, String name, String desp,
			String dealname);

	Long count();

	List<Config> listConfig();
}
