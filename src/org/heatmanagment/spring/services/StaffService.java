package org.heatmanagment.spring.services;

import java.sql.Timestamp;
import java.util.List;

import org.heatmanagment.hibernate.domain.StaffInfo;
import org.heatmanagment.spring.entity.StaffOut;

public interface StaffService {

	void saveOrUpdateCounty(Long id, String stfname, String stfnum,
			Timestamp startdate, String phone, String loginname, String pwd,
			String department, Long verifytype);

	void delete(Long id);

	List<StaffInfo> findPage(int start, int limit);

	List<StaffInfo> findAll();

	List<StaffOut> findByPjtid(Long pjtid);

	Long count();

}
