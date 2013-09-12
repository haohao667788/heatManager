package org.heatmanagment.hibernate.test;

import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DataWrapperForJsonTest {

	private ArrayList<ArrayList> items;
	private ObjectMapper mapper;

	@Before
	public void initialize() {
		this.items = new ArrayList<ArrayList>();
		this.mapper = new ObjectMapper();
	}

	// @Test
	@SuppressWarnings("unchecked")
	public void testWrapperEffect() {

		for (int i = 0; i < 10; i++) {
			ArrayList item = new ArrayList();
			item.add(new Long(i));
			item.add("社区" + String.valueOf(i));
			this.items.add(item);
		}
		try {
			String outCome = this.mapper.writeValueAsString(this.items);
			System.out.println(outCome);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testSystemTime() {

	}
}
