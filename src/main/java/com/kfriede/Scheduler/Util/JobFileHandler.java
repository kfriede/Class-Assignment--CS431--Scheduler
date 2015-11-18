package com.kfriede.Scheduler.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class JobFileHandler {
	
	private static final Logger log = Logger.getLogger( JobFileHandler.class.getName() );
	
	/**
	 * Opens file at specified location and reads in Job Name, Size parameters into a List
	 * @param fileName Location of file to open
	 * @return List of Job objects loaded from file
	 */
	public static List<Job> ParseJobFile(String fileName) {
		List<Job> jobs = new ArrayList<Job>();
		
		log.info("Started parsing job file at " + fileName);
		
		try {
			FileReader reader = new FileReader(fileName);
			String line = null;
			
            BufferedReader bufferedReader = new BufferedReader(reader);

            while((line = bufferedReader.readLine()) != null) {
                Job job = new Job();
                
                job.setJobName(line);
                job.setJobSize(Integer.parseInt(bufferedReader.readLine()));
                job.setTimeLeft(job.getJobSize());
                
                jobs.add(job);
                
                log.finer("Added job with name " + job.getJobName() + " and size " + job.getJobSize());
            }   

            bufferedReader.close();  
			
		} catch (FileNotFoundException e) {
			log.severe("File " + fileName + " not found, trace:\n" + e.toString());
		} catch (IOException e) {
			log.severe("Error reading file " + fileName + "\n" + e.toString());
		}
		
		return jobs;
	}
}
