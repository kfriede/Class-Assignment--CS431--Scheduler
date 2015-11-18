package com.kfriede.Scheduler;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.logging.Logger;

import com.kfriede.Scheduler.Algorithms.FirstComeFirstServe;
import com.kfriede.Scheduler.Algorithms.RoundRobin;
import com.kfriede.Scheduler.Algorithms.ShortestJobFirst;
import com.kfriede.Scheduler.Util.Job;
import com.kfriede.Scheduler.Util.JobFileHandler;
import com.kfriede.Scheduler.Util.Util;

public class Scheduler {
	private static String fileLocation = "src/main/resources/";
	
	private static final Logger log = Logger.getLogger( Scheduler.class.getName() );
	
	public static void main(String[] args) {
		
		File folder = new File(fileLocation);
		
		// get qualified names of all .txt files 
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".txt");
		    }
		});
		
		// create JobFileHandlers for all files
		for (File f : listOfFiles) {
			
			// load jobs from file
			List<Job> jobs = JobFileHandler.ParseJobFile(f.toString());
			
			FirstComeFirstServe fcfs = new FirstComeFirstServe();
			ShortestJobFirst sjf = new ShortestJobFirst();
			RoundRobin rr = new RoundRobin();
			
			Float cTime = fcfs.start(Util.cloneList(jobs));	
			System.out.println("Completed FCFS for file " + f + ".\n" + "Average Completion Time: " + cTime + " units\n");		

			cTime = sjf.start(Util.cloneList(jobs));
			System.out.println("Completed SJF for file " + f + ".\n" + "Average Completion Time: " + cTime + " units\n");
			
			rr.setTimeSlice(3);
			cTime = rr.start(Util.cloneList(jobs));
			System.out.println("Completed RR3 for file " + f + ".\n" + "Average Completion Time: " + cTime + " units\n");
			
			rr.setTimeSlice(4);
			cTime = rr.start(Util.cloneList(jobs));
			System.out.println("Completed RR4 for file " + f + ".\n" + "Average Completion Time: " + cTime + " units\n");
		}
		
	}
}
