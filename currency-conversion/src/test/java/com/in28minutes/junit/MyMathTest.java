package com.in28minutes.junit;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMathTest {
	
	MyMath myMath = new MyMath();

	@Before //it is run once before every test method
	public void before() {
		System.out.println("Before");
	}

	@After  //it is run after every test method
	public void after() {
		System.out.println("After");
	}

	@BeforeClass //it is run once before any of the test methods
	public static void beforeClass() {
		System.out.println("Before Class");
	}

	@AfterClass  //it is run once after any of the test methods
	public static void afterClass() {
		System.out.println("After Class");
	}

	// MyMath.sum
	// 1,2,3 => 6
	@Test
	public void sum_with3numbers() {
		System.out.println("Test1");
		assertEquals(6, myMath.sum(new int[] { 1, 2, 3 }));
	}

	@Test
	public void sum_with1number() {
		System.out.println("Test2");
		assertEquals(3, myMath.sum(new int[] { 3 }));
	}

}
