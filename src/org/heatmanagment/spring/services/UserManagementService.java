package org.heatmanagment.spring.services;

import java.sql.Timestamp;
import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.heatmanagment.hibernate.domain.UnitInfo;
import org.heatmanagment.hibernate.domain.UsersInfo;

public interface UserManagementService {

	List<UsersInfo> listItems(int start, int limit);

	void saveOrUpdate(Long uId, String username, String usertype,
			String address, String phone, Timestamp startDate,
			Timestamp contractDate, String contractType, String contractVer,
			String contractPic, String idPic, String houseIdPic,
			String housePic, Long cmtId, Long bldId, Long untId, Long mchId,
			Long pjtId, Double area, Double realarea, Double feearea,
			String feetype, Double feerate, Double discount, Double reducefee,
			String heatstate, Double heatbase, Double heatrate,
			String housetype, String desp);

	void delete(Long id);

	UsersInfo findById(Long usrid);

	Long count();
}
