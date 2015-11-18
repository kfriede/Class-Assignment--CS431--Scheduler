package com.kfriede.Scheduler.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Util {
	/**
	 * @return Average of given list
	 */
	public static float average(List<Integer> l) {
		float average = 0;
		
		for (Integer i : l) {
			average += i;
		}
		
		average = average / l.size();
		
		return average;
	}
	
	/**
	 * @return sorted list of given jobList
	 */
	public static List<Job> sortJobList(List<Job> jobList) {
		Collections.sort(jobList, new Comparator<Job>() {
	        public int compare(Job j1, Job j2) {
	            return j1.getTimeLeft() - j2.getTimeLeft();
	        }
	    });
		
		return jobList;
	}
	
	/**
	 * Clones a list to avoid overwriting original data
	 * @return cloned object of List 
	 */
	public static List<Job> cloneList(List<Job> jobList) {
	    List<Job> clonedList = new ArrayList<Job>(jobList.size());
	    for (Job job : jobList) {
	        clonedList.add(new Job(job));
	    }
	    return clonedList;
	}
}
