package io.github.hmatt33.memorysimulator.memobjects;

/**
 * Job class that defines all info of a job
 * @author Matt Hunt
 * @version 1.0
 */
public class Job {
	
	//fields
	private String name;
	private int size;
	private int status;
	
	/** 
	 * Blank Constructor
	 */
	public Job() {
		
	}
	
	/** 
	 * Constructor
	 * @param name
	 * @param size
	 */
	public Job(String name, int size) {
		this.name = name;
		this.size = size;
		status = 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param newName the name to set to
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param newSize the size to set to
	 */
	public void setSize(int newSize) {
		size = newSize;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param newStatus the status to set to
	 */
	public void setStatus(int newStatus) {
		status = newStatus;
	}
	
	/**
	 * turns numerical status into what it means in words
	 * 0 for waiting, 1 for running, -1 for finished
	 * else returns error
	 * @return String representation of int status meaning
	 */
	private String statusToString() {
		if(status == 0) {
			return "Waiting";
		} else if(status == 1) {
			return "Running";
		} else if(status == -1) {
			return "Finished";
		} else {
			return "Error";
		}
	}
	
	/**
	 * @return jobInfo, the string of all info about the job
	 */
	@Override
	public String toString() {
		String jobInfo = name + "                " + size + "                " + this.statusToString();
		return jobInfo;
	}
	
}
