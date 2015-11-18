package com.kfriede.Scheduler.Util;

/**
 * Custom implementation of a tuple to track process switching
 */
public class SegmentTuple {
	
	private Float startTime;
	private Float endTime;
	
	public SegmentTuple(float startTime, float endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public SegmentTuple() {
		
	}

	public float getStartTime() {
		return startTime;
	}

	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}

	public float getEndTime() {
		return endTime;
	}

	public void setEndTime(float endTime) {
		this.endTime = endTime;
	}
	
	
}
