package com.kfriede.Scheduler.Util;

import java.util.List;

import junit.framework.TestCase;

public class JobFileHandlerTest extends TestCase {
	private static String fileName = "src/main/resources/testFile.txt";
	
    public void testParseJobFile(){
        List<Job> returnedList = JobFileHandler.ParseJobFile(fileName);
        
        assertEquals(returnedList.get(0).getJobName(), "Job1");
        assertEquals(returnedList.get(0).getJobSize(), 6);
        
        assertEquals(returnedList.get(1).getJobName(), "Job2");
        assertEquals(returnedList.get(1).getJobSize(), 4);
        
        assertEquals(returnedList.get(2).getJobName(), "Job3");
        assertEquals(returnedList.get(2).getJobSize(), 10);
        
        assertEquals(returnedList.get(3).getJobName(), "Job4");
        assertEquals(returnedList.get(3).getJobSize(), 7);
    }
}
