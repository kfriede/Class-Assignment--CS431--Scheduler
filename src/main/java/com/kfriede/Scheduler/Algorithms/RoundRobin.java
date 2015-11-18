package com.kfriede.Scheduler.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kfriede.Scheduler.Util.Job;
import com.kfriede.Scheduler.Util.SegmentTuple;
import com.kfriede.Scheduler.Util.Util;

/**
 * <p>Round Robin scheduling algorithm
 */
public class RoundRobin implements SchedulingAlgorithm{
	private List<Job> jobList;
	private Map<Integer, List<SegmentTuple>> segmentTimesMap;
	
	private List<Integer> completionTimes;
	private int time;
	
	private Integer timeSlice;

	/**
	 * @return Average run time for given job list
	 */
	public float start(List<Job> jobList) {
		
		this.jobList = jobList;
		
		segmentTimesMap = new HashMap<Integer, List<SegmentTuple>>();
		completionTimes = new ArrayList<Integer>();
		time = 0;
		
		// repeats iteration through jobList if not all jobs are finished (timeLeft == 0)
		while (!checkComplete(jobList)) {
			// each iteration of this represents a "switch" in processes
			for (Job j : jobList) {
				// check if job has already been completed
				if (j.getTimeLeft() > 0) {
					SegmentTuple workingTuple = new SegmentTuple();
					
					// decrease job by timeSlice
					if (j.getTimeLeft() > timeSlice) {
						
						workingTuple.setStartTime(time);
						j.setTimeLeft(j.getTimeLeft() - timeSlice);
						
						time += timeSlice;
						workingTuple.setEndTime(time);
						
					// decrease job by timeSlice and mark complete
					} else if (j.getTimeLeft() == timeSlice) {
						
						workingTuple.setStartTime(time);
						j.setTimeLeft(j.getTimeLeft() - timeSlice);
						time += timeSlice;
						
						j.setCompletionTime(time);
						completionTimes.add(time);
						workingTuple.setEndTime(time);
						
					// job has time left that is less than time slice.  Advance time by amount of time left for given job
					} else {
						workingTuple.setStartTime(time);
						
						time += j.getTimeLeft();
						
						j.setCompletionTime(time);
						completionTimes.add(time);
						j.setTimeLeft(0);
						workingTuple.setEndTime(time);
					}
					
					// make record of this run
					addTupleToSegmentTimesMap(jobList.indexOf(j), workingTuple);
				}
			}
		}
		
		return Util.average(completionTimes);
	}
	
	/**
	 * Utility for offsetting work of adding a tuple to new/existing hash position
	 * @param index -- job index value (correlating to jobList) to add tuple to
	 * @param tupleToAdd
	 */
	private void addTupleToSegmentTimesMap(int index, SegmentTuple tupleToAdd) {
		if (segmentTimesMap.containsKey(index)) {
			segmentTimesMap.get(index).add(tupleToAdd);
		} else {
			List<SegmentTuple> workingList = new ArrayList<SegmentTuple>();
			workingList.add(tupleToAdd);
			segmentTimesMap.put(index, workingList);
		}
	}
	
	/**
	 * Sets value for how long each process should be run before next process is selected
	 */
	public void setTimeSlice(int timeSlice) {
		this.timeSlice = timeSlice;
	}
	
	public int getTimeSlice() {
		return timeSlice;
	}
	
	/**
	 * Utility for checking if jobList is complete (all jobs have 0 time left)
	 */
	private boolean checkComplete(List<Job> jobList) {
		for (Job j : jobList) {
			if (j.getTimeLeft() > 0)
				return false;
		}
		
		return true;
	}
	
	public List<Job> getJobList() {
		return jobList;
	}
	
	public Map<Integer, List<SegmentTuple>> getSegmentTimesMap() {
		return segmentTimesMap;
	}
}
