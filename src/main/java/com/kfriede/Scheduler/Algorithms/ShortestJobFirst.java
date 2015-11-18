package com.kfriede.Scheduler.Algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kfriede.Scheduler.Util.Job;
import com.kfriede.Scheduler.Util.SegmentTuple;
import com.kfriede.Scheduler.Util.Util;

/**
 * <p>Runs job list with Shortest Job First algorithm
 * <p>Sorts list, then calls First Come First Serve on sorted list
 */
public class ShortestJobFirst implements SchedulingAlgorithm{
	
	private List<Job> jobList;
	private Map<Integer, List<SegmentTuple>> segmentTimesMap;

	/**
	 * @return Average run time for given job list
	 */
	public float start(List<Job> jobList) {
		
		this.jobList = jobList;
		segmentTimesMap = new HashMap<Integer, List<SegmentTuple>>();
		
		Util.sortJobList(this.jobList);
		
		FirstComeFirstServe fcfs = new FirstComeFirstServe();
		float cTime = fcfs.start(this.jobList);
		
		this.jobList = fcfs.getJobList();
		this.segmentTimesMap = fcfs.getSegmentTimesMap();
		
		return cTime;
	}
	
	public List<Job> getJobList() {
		return jobList;
	}
	
	public Map<Integer, List<SegmentTuple>> getSegmentTimesMap() {
		return segmentTimesMap;
	}
}
