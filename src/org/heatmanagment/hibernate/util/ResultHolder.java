package org.heatmanagment.hibernate.util;

import java.util.ArrayList;

/**
 * 
 * @author Desmond
 * 
 */
public class ResultHolder {

	private ArrayList<ArrayList<Object>> data;

	public ResultHolder() {
		this.data = new ArrayList<ArrayList<Object>>();
	}

	public void add(Object key, Object value) {
		ArrayList<Object> temp = new ArrayList<Object>(2);
		temp.add(key);
		temp.add(value);
		this.data.add(temp);
	}

	public ArrayList<ArrayList<Object>> getData() {
		return this.data;
	}
}
