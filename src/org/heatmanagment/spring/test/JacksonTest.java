package org.heatmanagment.spring.test;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.domain.DistrictInfo;
import org.heatmanagment.spring.entity.DistrictOut;
import org.heatmanagment.spring.entity.SuccessOut;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JacksonTest {
	private ObjectMapper mapper;

	@Before
	public void initialize() {
		this.mapper = new ObjectMapper();
	}

	@Test
	public void testObjectToJson() {
		DistrictInfo info = new DistrictInfo();
		info.setDstid(new Long(199549511454l));
		info.setDstname("tiantong");
		info.setComm("no comment");
		String temp;
		try {
			temp = this.mapper.writeValueAsString(info);
			System.out.println(temp);
			DistrictInfo t = this.mapper.readValue(temp, DistrictInfo.class);
			System.out.println(t.getDstid() + " " + t.getDstname() + " "
					+ t.getComm());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSuccess() {

	}

	// @Test
	public void testDistrictOutObject() {
		DistrictInfo info = new DistrictInfo();
		info.setDstid(103948956l);
		info.setDstname("aaaa");
		info.setComm("12456");
		List<DistrictInfo> infos = new ArrayList<DistrictInfo>();
		infos.add(info);
		DistrictOut out = new DistrictOut();
		out.setSuccess(true);
		out.setData(infos);

		String temp;
		try {
			temp = this.mapper.writeValueAsString(out);
			System.out.println(temp);
			DistrictOut in = this.mapper.readValue(temp, DistrictOut.class);
			System.out.println(in.isSuccess() + " ");
			List<DistrictInfo> di = in.getData();
			for (DistrictInfo t : di) {
				System.out.println(t.getDstid() + " " + t.getDstname() + " "
						+ t.getComm());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
