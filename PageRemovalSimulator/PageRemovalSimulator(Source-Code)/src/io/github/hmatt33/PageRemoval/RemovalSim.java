package io.github.hmatt33.PageRemoval;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**This class holds that logic that checks to see if a job can be added and how to add it
 * @author Matt Hunt
 * @version 1.0
 */
public class RemovalSim {
	
	//jobList holds all jobs created to keep track of them all 
	//makes sure there are no duplicate jobs
	ArrayList<Job> jobList = new ArrayList<>();
	//stack that holds the jobs in memory, used for LRU, Stacks are LIFO
	Stack<Job> jobsS = new Stack<>();
	//Queue that holds the jobs in memory, used for FIFO, Queues are FIFO
	Queue<Job> jobsQ = new LinkedList<>();
	
	int status = -2; //default value, -2 not used in program, only for initialization
	
	/**Checks job and sets its status
	 * @param name
	 * @return
	 */
	public int checkJob(String name) {
		//if 1 job already exists and is in mem
		//if 0 job already exists but not in mem
		//if -1 job doesn't exist
		for(int i = 0; i < jobList.size(); i++) {
			if(jobList.get(i).getName().equals(name)) {
				if(jobList.get(i).isInMem()) {
					status = 1;
				} else {
					status = 0;
				}
			} else {
				status = -1;
			}
		}
		return status;
	}
	
	/**adds job to queue and stack and the jobList
	 * uses the status from checkJob to figure out how to add and when
 	 * @param name
	 */
	public void addJob(String name) {
		if(checkJob(name) == -1  || checkJob(name) == -2) { //-2 is default value (needed to be initialized))
			//if job is -1 (doesn't exist) create new job
			//add to jobList
			//add to queue and stack (so it can be used with FIFO and LRU)
			//add job is used only when job is able to be in memory so inMem set to true
			Job newJob = new Job(name, false);
			jobList.add(newJob);
			jobsQ.add(newJob);
			jobsS.add(newJob);
			newJob.setInMem(true);
		} else if(checkJob(name) == 0) {
			//if job is 0 (exists but not in memory)
			//does not create a new job
			//searches list for existing job
			//adds that to list, queue and stack, and sets inMem to true
			for(int i = 0; i < jobList.size(); i++) {
				if(jobList.get(i).getName().equals(name)) {
					jobsQ.add(jobList.get(i));
					jobsS.add(jobList.get(i));
					jobList.get(i).setInMem(true);
				}
			}
		}
	}
	
	
	
	/**determines what to remove for FIFO method, uses poll on a Queue which is FIFO
	 * @param name
	 */
	public void fifo(String name) { 
		for(int i = 0; i < jobList.size(); i++) { 
			if(jobList.get(i).getName().equals(name)) {
				jobsQ.poll(); 
				} 
			} 
		}

	/**determines what to remove for LRU method, uses pop on a stack which is LIFO
	 * @param name
	 */
	public void lru(String name) { 
		for(int i = 0; i < jobList.size(); i++) { 
			if(jobList.get(i).getName().equals(name)) {
				jobsS.pop(); 
				} 
			} 
		}

	
}
