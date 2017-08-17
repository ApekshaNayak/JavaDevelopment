package com.project.junitEx;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest{
    @Test
	public void testMax() {
    	assertEquals(5,App.findMax(new int[]{1,5,3,2,4}));
    	assertEquals(10,App.findMax(new int[]{7,10,8}));
    }
}
