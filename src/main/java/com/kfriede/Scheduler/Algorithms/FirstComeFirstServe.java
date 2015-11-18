package com.kfriede.Scheduler.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.kfriede.Scheduler.Util.Job;
import com.kfriede.Scheduler.Util.SegmentTuple;
import com.kfriede.Scheduler.Util.Util;


public class FirstComeFirstServe implements SchedulingAlgorithm{
	private static final Logger log = Logger.getLogger( FirstComeFirstServe.class.getName() );
	
	private List<Job> jobList;
	private Map<Integer, List<SegmentTuple>> segmentTimesMap;
	
	private List<Integer> completionTimes;
	private int time;
	
	/**
	 * @return Average run time for given job list
	 */
	public float start(final List<Job> jobList) {
		
		this.jobList = jobList;
		
		segmentTimesMap = new HashMap<Integer, List<SegmentTuple>>();
		completionTimes = new ArrayList<Integer>();
		time = 0;
		
		// each iteration of this represents a "switch" in processes
		for (Job j : this.jobList) {
			SegmentTuple workingTuple = new SegmentTuple();
			
			// open process
			workingTuple.setStartTime((float) time);
			time += j.getJobSize();
			
			// close process
			workingTuple.setEndTime((float) time);
			j.setCompletionTime(time);
			completionTimes.add(time);
			
			// build new list for tuple storage and write to segmentTimesMap
			List<SegmentTuple> workingList = new ArrayList<SegmentTuple>();
			workingList.add(workingTuple);
			segmentTimesMap.put(this.jobList.indexOf(j), workingList);
			
			log.fine("Job " + j.getJobName() + " finished at time " + time);
		}
		
		return Util.average(completionTimes);
	}
	
	public List<Job> getJobList() {
		return jobList;
	}
	
	public Map<Integer, List<SegmentTuple>> getSegmentTimesMap() {
		return segmentTimesMap;
	}
	
}
