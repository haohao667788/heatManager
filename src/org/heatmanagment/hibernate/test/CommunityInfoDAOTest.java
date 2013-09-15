package org.heatmanagment.hibernate.test;

import java.util.List;

import org.heatmanagment.hibernate.domain.CommunityInfo;
import org.heatmanagment.hibernate.domain.CommunityInfoDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class CommunityInfoDAOTest {

	private CommunityInfoDAO dao;

	@Before
	public void initialize() {
		this.dao = CommunityInfoDAO
				.getFromApplicationContext(new ClassPathXmlApplicationContext(
						"applicationContext.xml"));
	}

	@Test
	public void testDelete() {
		String name = "创新苑";
		CommunityInfo info = new CommunityInfo();
		List<CommunityInfo> infos = this.dao.findByCmtname(name);
		for (CommunityInfo temp : infos) {
			info.setCmtid(temp.getCmtid());
			this.dao.delete(info);
		}
		List<CommunityInfo> infos2 = this.dao.findByCmtname(name);
		assertTrue("The infos returned after deletion should be empty",
				infos2.isEmpty());

	}

	public void testCommunityInfoDao() {
		String name = "创新苑";
		String brief = "创新";
		String address = "创新中路399弄";
		CommunityInfo info = null;
		this.dao.save(info);

		List<CommunityInfo> infos = this.dao.findByCmtname(name);

		for (CommunityInfo temp : infos) {
			assertEquals("The name must be identical", name, temp.getCmtname());
			assertEquals("brief name must be the same", brief,
					temp.getBriefname());
			assertEquals("Address must be identical", address,
					temp.getCmtaddress());
		}
	}
}
