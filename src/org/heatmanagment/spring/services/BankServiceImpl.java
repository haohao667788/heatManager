package org.heatmanagment.spring.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.BankInfo;
import org.heatmanagment.hibernate.domain.BankInfoDAO;
import org.heatmanagment.hibernate.domain.CourseInfo;
import org.heatmanagment.hibernate.domain.CourseInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bankService")
@Transactional
public class BankServiceImpl implements BankService {

	@Autowired
	private BankInfoDAO bdao;

	@Autowired
	private CourseInfoDAO cdao;

	@Override
	public List<BankInfo> listItems(int start, int limit) {
		return this.bdao.findPage(start, limit);
	}

	@Override
	public void delete(Long id) {
		BankInfo bnk = this.bdao.findById(id);
		bnk.setIsvalid(false);
		this.bdao.attachDirty(bnk);
	}

	@Override
	public void saveOrUpdate(Long id, String bnknum, String name,
			String accNum, String desp, Long crsid) {
		BankInfo bnk = new BankInfo();
		bnk.setIsvalid(true);
		bnk.setBnkid(id);
		bnk.setBnknum(bnknum);
		bnk.setBnkname(name);
		bnk.setAccountnum(accNum);
		bnk.setDesp(desp);
		if (crsid != null) {
			CourseInfo crs = new CourseInfo();
			crs.setCrsid(crsid);
			bnk.setCourseInfo(crs);
		}
		this.bdao.attachDirty(bnk);
	}

	@Override
	public Long count() {
		return this.bdao.count();
	}

	@Override
	public List<CourseInfo> queryCourse() {
		return this.cdao.findAll();
	}

}
