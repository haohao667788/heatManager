package org.heatmanagment.spring.services;

import java.sql.Timestamp;
import java.util.List;

import org.heatmanagment.hibernate.domain.BuildingInfo;
import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.domain.ProjectInfo;
import org.heatmanagment.hibernate.domain.UnitInfo;
import org.heatmanagment.hibernate.domain.UsersInfo;
import org.heatmanagment.hibernate.domain.UsersInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userManagementService")
@Transactional
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UsersInfoDAO dao;

	@Override
	public List<UsersInfo> listItems(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public void delete(Long id) {
		UsersInfo users = this.dao.findById(id);
		users.setIsvalid(false);
		this.dao.attachDirty(users);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public void saveOrUpdate(Long uId, String username, String usertype,
			String address, String phone, Timestamp startDate,
			Timestamp contractDate, String contractType, String contractVer,
			String contractPic, String idPic, String houseIdPic,
			String housePic, Long cmtId, Long bldId, Long untId, Long mchId,
			Long pjtId, Double area, Double realarea, Double feearea,
			String feetype, Double feerate, Double discount, Double reducefee,
			String heatstate, Double heatbase, Double heatrate,
			String housetype, String desp) {
		UsersInfo user = new UsersInfo();
		user.setAddress(address);
		user.setUsrid(uId);
		user.setArea(area);
		user.setContractdate(contractDate);
		user.setContractpic(contractPic);
		user.setContracttype(contractType);
		user.setContractver(contractVer);
		user.setDesp(desp);
		user.setDiscount(discount);
		user.setFeearea(feearea);
		user.setFeerate(feerate);
		user.setFeetype(feetype);
		user.setHeatbase(heatbase);
		user.setHeatrate(heatrate);
		user.setHeatstate(heatstate);
		user.setHouseidpic(houseIdPic);
		user.setHousepic(housePic);
		user.setHousetype(housetype);
		user.setIdpic(idPic);
		user.setIsvalid(true);
		user.setPhone(phone);
		user.setRealarea(realarea);
		user.setReducefee(reducefee);
		user.setStartdate(startDate);
		user.setUsertype(usertype);
		user.setUsrname(username);
		if (pjtId != null) {
			ProjectInfo pjt = new ProjectInfo();
			pjt.setPjtid(pjtId);
			user.setProjectInfo(pjt);
		}
		if (mchId != null) {
			MachinesetInfo mch = new MachinesetInfo();
			mch.setMchid(mchId);
			user.setMachinesetInfo(mch);
		}
		if (untId != null) {
			UnitInfo unt = new UnitInfo();
			unt.setUntid(untId);
			user.setUsrid(untId);
		}
		if (bldId != null) {
			BuildingInfo bld = new BuildingInfo();
			bld.setBldid(bldId);
			user.setBuildingInfo(bld);
		}
		if (cmtId != null) {
			CommunityInfo cmt = new CommunityInfo();
			cmt.setCmtid(cmtId);
			user.setCommunityInfo(cmt);
		}
		this.dao.attachDirty(user);
	}

	@Override
	public UsersInfo findById(Long usrid) {
		return this.dao.findById(usrid);
	}
}
