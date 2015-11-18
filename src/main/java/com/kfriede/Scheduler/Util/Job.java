package com.kfriede.Scheduler.Util;

public class Job {
	private String jobName;
	private int jobSize;
	private int timeLeft;
	private int completionTime;
	
	public Job() {
		
	}
	
	public Job(Job copyJob) {
		jobName = copyJob.jobName;
		jobSize = copyJob.jobSize;
		timeLeft = copyJob.timeLeft;
		completionTime = copyJob.completionTime;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getJobSize() {
		return jobSize;
	}

	public void setJobSize(int jobSize) {
		this.jobSize = jobSize;
	}
	
	public int getTimeLeft() {
		return timeLeft;
	}
	
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	
	public int getCompletionTime() {
		return completionTime;
	}
	
	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}
	
}