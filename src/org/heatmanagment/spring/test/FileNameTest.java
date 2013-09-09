package org.heatmanagment.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FileNameTest {

	@Test
	public void testFileSuffixReading() {
		String fileName = "diamdigmdkak.jpg";
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		System.out.println(suffix);
	}
}
