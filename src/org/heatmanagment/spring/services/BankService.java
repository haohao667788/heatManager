package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BankInfo;
import org.heatmanagment.hibernate.domain.CourseInfo;

public interface BankService {

	List<BankInfo> listItems(int start, int limit);

	void delete(Long id);

	void saveOrUpdate(Long id, String bnknum, String name, String accNum,
			String desp, Long crsid);

	Long count();

	List<CourseInfo> queryCourse();
}
