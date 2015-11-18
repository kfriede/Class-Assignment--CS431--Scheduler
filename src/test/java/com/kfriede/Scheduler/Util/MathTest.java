package com.kfriede.Scheduler.Util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class MathTest extends TestCase {
	
    public void testAverage(){
    	List<Integer> fillList = new ArrayList<Integer>();
    
    	fillList.add(5);
    	fillList.add(6);
    	fillList.add(7);
    	
    	assertEquals(Util.average(fillList), (float)6);
    }
}
