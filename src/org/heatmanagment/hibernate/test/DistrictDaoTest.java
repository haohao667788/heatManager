package org.heatmanagment.hibernate.test;

import java.util.List;

import org.heatmanagment.hibernate.domain.CommunityInfoDAO;
import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.hibernate.domain.DistrictInfoDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(JUnit4.class)
public class DistrictDaoTest {

	private DistrictInfoDAO dao;

	@Before
	public void initialize() {
		this.dao = DistrictInfoDAO
				.getFromApplicationContext(new ClassPathXmlApplicationContext(
						"applicationContext.xml"));
	}

	// @Test
	public void testAttachDirty() {
		DistrictInfo instance = new DistrictInfo();
		String name = "天通苑";
		instance.setDstname(name);
		instance.setComm("no comment");
		this.dao.attachDirty(instance);

		Long id = null;
		List<DistrictInfo> infos = this.dao.findByDstname(name);
		for (DistrictInfo info : infos) {
			id = info.getDstid();
			System.out.println(info.getDstid() + " " + info.getDstname() + " "
					+ info.getComm());
		}
	}

	@Test
	public void testAttachDirtyAgain() {
		DistrictInfo in2 = new DistrictInfo();
		String name2 = "立水桥";
		String name = "天通苑";

		Long id = null;
		in2.setDstname(name2);

		List<DistrictInfo> infos = this.dao.findByDstname(name);
		for (DistrictInfo info : infos) {
			id = info.getDstid();
			System.out.println(info.getDstid() + " " + info.getDstname() + " "
					+ info.getComm());
		}

		in2.setDstid(id);
		this.dao.attachDirty(in2);

	}
}
