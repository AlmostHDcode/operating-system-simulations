package io.github.hmatt33.JobScheduling;

/**This class holds all the information on Job objects
 * @author Matt Hunt
 * @version 1.0
 */
public class Job {
	
	//fields
	private String name;
	private int arrival;
	private int cycle;
	private int finishTime;
	private int waitTime;
	private int turnaround;
	private boolean done;
	
	/**Job Constructor 
	 * @param name, name of job
	 * @param arrival, time of arrival
	 * @param cycle, cpu cycle of job
	 */
	public Job(String name, int arrival, int cycle) {
		this.name = name;
		this.arrival = arrival;
		this.cycle = cycle;
		finishTime = 0;
		waitTime = 0;
		turnaround = 0;
		done = false;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the arrival
	 */
	public int getArrival() {
		return arrival;
	}

	/**
	 * @param arrival the arrival to set
	 */
	public void setArrival(int arrival) {
		this.arrival = arrival;
	}

	/**
	 * @return the cycle
	 */
	public int getCycle() {
		return cycle;
	}

	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	/**
	 * @return the finishTime
	 */
	public int getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime the finishTime to set
	 */
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return the waitTime
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * @param waitTime the waitTime to set
	 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * @return the turnaround
	 */
	public int getTurnaround() {
		return turnaround;
	}

	/**
	 * @param turnaround the turnaround to set
	 */
	public void setTurnaround(int turnaround) {
		this.turnaround = turnaround;
	}

	/**
	 * @return the done
	 */
	public boolean isDone() {
		return done;
	}

	/**
	 * @param done the done to set
	 */
	public void setDone(boolean done) {
		this.done = done;
	}
	
	/* 
	 * to string method that prints all information about a job	
	 */
	@Override
	public String toString() {
		String result = name + "\t \t" + arrival + "\t \t" + cycle + "\t \t" + finishTime + "\t \t" + waitTime + "\t \t" + turnaround;
		return result;
	}
	
	
}
