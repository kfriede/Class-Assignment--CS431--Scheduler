package com.kfriede.Scheduler.Algorithms;

import java.util.List;
import java.util.Map;

import com.kfriede.Scheduler.Util.Job;
import com.kfriede.Scheduler.Util.SegmentTuple;

public interface SchedulingAlgorithm {
	public float start(final List<Job> jobList);
	public List<Job> getJobList();
	public Map<Integer, List<SegmentTuple>> getSegmentTimesMap();
}
