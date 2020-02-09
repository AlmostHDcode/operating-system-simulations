 package io.github.hmatt33.memorysimulator.memobjects;

/**
 * Partition class that defines all info about partitions
 * @author Matt Hunt
 * @version 1.0
 */
public class Partition {
	
	//fields
	private String name;
	private int size;
	private int memAddress;
	private boolean isFree;
	private String runningJobName;
	private int runningJobSize;
	private int runningJobStatus;
	private int fragmentation;
	
	/**
	 * blank Constructor
	 */
	public Partition() {
		
	}
	
	/**
	 * Constructor
	 * @param name
	 * @param size
	 */
	public Partition(String name, int size) {
		this.name = name;
		this.size = size;
		memAddress = 0;
		isFree = true;
		runningJobStatus = 0;
		runningJobName = "null";
		runningJobSize = 0;
		fragmentation = 0;
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
	public void setName(String name) {
		this.name = name;
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
	 * @return mem address
	 */
	public int getMemAddress() {
		return memAddress;
	}
	
	/**
	 * @param mem, the new mem address
	 */
	public void setMemAddress(int mem) {
		memAddress = mem;
	}

	/**
	 * @return whether or not the partition is free or not
	 */
	public boolean isFree() {
		return isFree;
	}

	/**
	 * @param isFree set isFree to true or false
	 */
	public void setIsFree(boolean newIsFree) {
		isFree = newIsFree;
	}
	
	/**
	 * @return name of job in partition
	 */
	public String getRunningJobName() {
		return runningJobName;
	}
	
	/**
	 * @param newJobName new name to give job
	 */
	public void setRunningJobName(String newJobName) {
			runningJobName = newJobName;
	}
	
	/**
	 * @return size of job in partition
	 */
	public int getRunningJobSize() {
		return runningJobSize;
	}
	
	/**
	 * @param newJobSize new size to give job
	 */
	public void setRunningJobSize(int newJobSize) {
		runningJobSize = newJobSize;
	}
	
	/**
	 * @return status of job in partition
	 */
	public int getRunningJobStatus() {
		return runningJobStatus;
	}
	
	/**
	 * @param newStatus new status of job
	 */
	public void setRunningJobStatus(int newStatus) {
		runningJobStatus = newStatus;
	}
	
	/**
	 * @return the amount of fragmentation
	 */
	public int getFrag() {
		return fragmentation;
	}
	
	//used for fixed partitions
	/**
	 * sets frag in fixed partition
	 */
	public void setFrag() {
		fragmentation = size - runningJobSize;
	}
	
	//used for dynamic partitions
	/**
	 * @param newFrag sets frag in dymanic partition
	 */
	public void setFrag(int newFrag) {
		fragmentation = newFrag;
	}
	
	/**
	 * Takes true or false of isFree() and turns it into words
	 * @return Free if the partition is free or Busy if partition is filled
	 */
	private String isFreeToString() {
		if(this.isFree()) {
			return "Free";
		} else {
			return "Busy";
		}
	}
	
	/**
	 * @return partitionInfo, string of all info about partition
	 */
	@Override
	public String toString() {
		if(!runningJobName.equals("null")) {
			String partitionInfo = name + "        " + size + "        " + memAddress + "        " + runningJobName + "        " + runningJobSize + "        " + this.isFreeToString() + "        " + fragmentation;
			return partitionInfo;
		} else {
			String partitionInfo = name + "      " + size + "        " + memAddress + "                               " + this.isFreeToString() + "       " + fragmentation;
			return partitionInfo;
		}
	}
	
}
