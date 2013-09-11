package org.heatmanagment.spring.test.services;

import java.util.List;

import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.spring.services.DistrictService;
import org.heatmanagment.spring.services.DistrictServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "../../../../../applicationContext.xml")
@Transactional
public class ServiceDistrictTest {

	@Autowired
	private DistrictService districtSerivce;

	@Before
	public void initialize() {
		// this.districtSerivce = new DistrictServiceImpl();
	}

	@Test
	public void testSaveOrUpdate() {
		// test save
		String name = "唐镇苑";
		String name1 = "川沙苑";
		String comm = "创新中路399弄";
		String comm1 = "创新路500弄";
		Long id = null;
		this.districtSerivce.saveOrUpdateDistrict(null, name, comm);
		List<DistrictInfo> infos = this.districtSerivce.findPage(0, 999);
		for (DistrictInfo info : infos) {
			id = info.getDstid();
			assertEquals("name must be equal", name, info.getDstname());
			assertEquals("comm must be equal", comm, info.getComm());
		}

		// test update
		this.districtSerivce.saveOrUpdateDistrict(id, name1, comm1);
		List<DistrictInfo> temp = this.districtSerivce.findPage(0, 5);
		assertEquals("There can only be one record", 1, temp.size());
		for (DistrictInfo i : temp) {
			assertEquals("name1 must be equal", name1, i.getDstname());
			assertEquals("comm1 must be equal", comm1, i.getComm());
		}
	}
}
