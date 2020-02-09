package io.github.hmatt33.PageRemoval;

/** Job class that holds all info on job pages
 * @author Matt Hunt
 * @version 1.0
 */
public class Job {
	private String name;
	private boolean inMem;
	
	/**Constructor, sets inMem default to false
	 * @param name, name of job
	 * @param inMem, status of job in memory
	 */
	public Job(String name, boolean inMem) {
		this.setName(name);
		inMem = false;
	}

	/** returns the name of the job
	 * @return
	 */
	public String getName() {
		return name;
	}

	/** sets the name of the job with newName
	 * @param newName
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**returns true if job is loaded in memory, false otherwise
	 * @return
	 */
	public boolean isInMem() {
		return inMem;
	}

	/**sets the status of if the job is in memory with newInMem
	 * @param newInMem
	 */
	public void setInMem(boolean newInMem) {
		inMem = newInMem;
	}
	
}
