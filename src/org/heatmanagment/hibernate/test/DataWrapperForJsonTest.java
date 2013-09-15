package org.heatmanagment.hibernate.test;

import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.heatmanagment.hibernate.util.MapperTool;
import org.heatmanagment.spring.entity.SuccessOut;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

import bsh.This;

import com.sun.net.httpserver.Authenticator.Success;

@RunWith(JUnit4.class)
public class DataWrapperForJsonTest {

	private ArrayList<ArrayList> items;
	private ObjectMapper mapper;
	private SuccessOut out;
	private MapperTool tool;

	@Before
	public void initialize() {
		this.items = new ArrayList<ArrayList>();
		this.mapper = new ObjectMapper();
		this.out = new SuccessOut();
		this.tool = new MapperTool();
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
	public void testSuccessRetort() {
		System.out.println(this.tool.successRetort());

		System.out.println(this.tool.failRetort("error"));

	}
}
