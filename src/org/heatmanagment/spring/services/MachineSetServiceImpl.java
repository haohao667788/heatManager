package org.heatmanagment.spring.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.heatmanagment.hibernate.domain.ClassInfo;
import org.heatmanagment.hibernate.domain.HeatsourceInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfo;
import org.heatmanagment.hibernate.domain.MachinesetInfoDAO;
import org.heatmanagment.spring.entity.SuccessOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("machineSetService")
@Transactional
public class MachineSetServiceImpl implements MachineSetService {

	@Autowired
	private MachinesetInfoDAO dao;

	@Override
	@SuppressWarnings("unchecked")
	public List<MachinesetInfo> listItems(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public void saveOrUpdate(Long mId, String name, String gis, String desp,
			Long srcid, Long clsid) {
		MachinesetInfo mch = new MachinesetInfo();
		mch.setMchid(mId);
		mch.setMchname(name);
		mch.setGis(gis);
		mch.setDesp(desp);
		mch.setIsvalid(true);

		if (srcid != null) {
			HeatsourceInfo src = new HeatsourceInfo();
			src.setSrcid(srcid);
			mch.setHeatsourceInfo(src);
		}
		if (clsid != null) {
			ClassInfo cls = new ClassInfo();
			cls.setClsid(clsid);
			mch.setClassInfo(cls);
		}
		this.dao.attachDirty(mch);
	}

	@Override
	public void delete(Long mId) {
		MachinesetInfo mch = this.dao.findById(mId);
		mch.setIsvalid(false);
		this.dao.attachDirty(mch);
	}

	@Override
	public List<MachinesetInfo> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<MachinesetInfo> findPage(int start, int limit) {
		return this.dao.findPage(start, limit);
	}

	@Override
	public Long count() {
		return this.dao.count();
	}

	@Override
	public List<Object> findMchByBld(Long bldid) {
		List<Object[]> temp = this.dao.findByBldId(bldid);
		List<Object> data = new ArrayList<Object>();
		for (Object[] i : temp) {
			MachinesetInfo mch = new MachinesetInfo();
			mch.setMchid(((BigDecimal) i[0]).longValue());
			mch.setMchname(i[1].toString());
			data.add(mch);
		}
		return data;
	}
}
