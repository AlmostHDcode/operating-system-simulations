package io.github.hmatt33.memorysimulator.allocators;

import io.github.hmatt33.memorysimulator.memobjects.Job;
import io.github.hmatt33.memorysimulator.memobjects.Partition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * this class handles the logic of allocating jobs with fixed partitions
 * @author Matt Hunt
 * @version 1.0
 */
public class FixedMemoryAllocator {
	//lists
	private ArrayList<Job> jobs;
	private ArrayList<Partition> partitions;
	private ArrayList<Partition> sortedPartitions;
	private ArrayList<Partition> badSortedPartitions;
	//memory address of partitions
	private int memAddress = 0;
	
	//Strings that will hold the results of the allocations
	String printedJobs = "";
	String printedPartitions = "";
	String printedSnapshot = "";
	
	//default display headers
	String defaultJob = "Job Name:" + "    " + "Job Size:" + "    " + "Job Status:"; 
	String defaultP = "P Name:" + "    " + "P Size:" + "    " + "Mem Addr:" + "   " + "Job:" + "    " + "Job Size:" + "    " + "Status:" + "    " + "Frag:";
	
	
	/**
	 * Constructor
	 * initializes the main lists
	 */
	public FixedMemoryAllocator() {
		jobs = new ArrayList<Job>();
		partitions = new ArrayList<Partition>();
	}
	
	/**
	 * @param name, name of job
	 * @param size, size that job requires
	 * adds jobs to job list
	 */
	public void addJob(String name, int size) {
		Job j = new Job(name, size);
		jobs.add(j);
	}
	
	/**
	 * @param name, name of partition
	 * @param size, size of partition
	 * adds partitions to main partition list
	 */
	public void addPartition(String name, int size) {
		memAddress = memAddress + size;
		Partition p = new Partition(name, size);
		p.setMemAddress(memAddress);
		partitions.add(p);
	}
	
	/**
	 * @param jobName, name of job to end
	 * ends the selected job
	 * sets all job fields of partition to finished and frees the space
	 */
	public void endJob(String jobName) {
		for(int i = 0; i < partitions.size(); i++) {
			if(partitions.get(i).getRunningJobName().equals(jobName)) {
				partitions.get(i).setRunningJobStatus(-1);
				partitions.get(i).setRunningJobName("null");
				partitions.get(i).setRunningJobSize(0);
				partitions.get(i).setIsFree(true);
			}
		}
		//job set to finished
		for(int i = 0; i < jobs.size(); i++) {
			if(jobs.get(i).getName().equals(jobName)) {
				jobs.get(i).setStatus(-1);
			}
		}
	}
	
	/**
	 * @param jobName, name of job to end in sorted list
	 */
	public void endJobBest(String jobName) {
		for(int i = 0; i < sortedPartitions.size(); i++) {
			if(sortedPartitions.get(i).getRunningJobName().equals(jobName)) {
				//partition memory freed
				sortedPartitions.get(i).setRunningJobStatus(-1);
				sortedPartitions.get(i).setRunningJobName("null");
				sortedPartitions.get(i).setRunningJobSize(0);
				sortedPartitions.get(i).setIsFree(true);
			}
		}
		//jobs set to finished
		for(int i = 0; i < jobs.size(); i++) {
			if(jobs.get(i).getName().equals(jobName)) {
				jobs.get(i).setStatus(-1);
			}
		}
	}
	
	/**
	 * @param jobName, name of job to end in worstFit list
	 */
	public void endJobWorst(String jobName) {
		for(int i = 0; i < badSortedPartitions.size(); i++) {
			if(badSortedPartitions.get(i).getRunningJobName().equals(jobName)) {
				badSortedPartitions.get(i).setRunningJobStatus(-1);
				badSortedPartitions.get(i).setRunningJobName("null");
				badSortedPartitions.get(i).setRunningJobSize(0);
				badSortedPartitions.get(i).setIsFree(true);
			}
		}
		for(int i = 0; i < jobs.size(); i++) {
			if(jobs.get(i).getName().equals(jobName)) {
				jobs.get(i).setStatus(-1);
			}
		}
	}
	
	/**
	 * lists are cleared back to empty
	 */
	public void clearLists() {
		if(partitions != null) {
			partitions.clear();
			memAddress = 0;
		}
		if(jobs != null) {
			jobs.clear();
		}
		if(sortedPartitions != null) {
			sortedPartitions.clear();
			memAddress = 0;
		}
		if(badSortedPartitions != null) {
			badSortedPartitions.clear();
			memAddress = 0;
		}
	}
	
	/**
	 * @return total, the total amont of fragmentation
	 */
	public int totalFragmentation() {
		int total = 0;
		for(int i = 0; i < partitions.size(); i++) {
			total = total + partitions.get(i).getFrag();
		}
		return total;
	}
	
	/**helper method to bestFitAllocation, sorts the array of partitions from smallest to largest size
	 * @return the new sorted array list
	 */
	private ArrayList<Partition> sizeSort() {
		
		ArrayList<Partition> sortedPartitions = (ArrayList<Partition>) partitions.clone();
		
		//comparator takes two partitions and sorts them by size
		Collections.sort(sortedPartitions, new Comparator<Partition>() {
			@Override
			public int compare(Partition p1, Partition p2) {
				// TODO Auto-generated method stub
				if(p1.getSize() > p2.getSize()) {
					return 1;
				} else if(p1.getSize() < p2.getSize()) {
					return -1;
				} else {
					return 0;
				}
			}
			
		});
		
		return sortedPartitions;
	}
	
	/**helper method to WorstFitAllocation, sorts the array of partitions from largest to smallest size
	 * @return the new reverse sorted array list
	 */
	private ArrayList<Partition> badSizeSort() {
		ArrayList<Partition> badSortedPartitions = sizeSort();
		
		Collections.reverse(badSortedPartitions);
		
		return badSortedPartitions;
	}
	
	/**
	 * jobs are allocated into the fixed partitions with the first fit algorithm
	 */
	public void firstFitAllocation() {
		for(int i = 0; i < jobs.size(); i++) {
			for(int j = 0; j < partitions.size(); j++) {
				if(partitions.get(j).isFree() && jobs.get(i).getStatus() == 0) {
					if(jobs.get(i).getSize() <= partitions.get(j).getSize()) {
						//job and partition updated once allocated
						jobs.get(i).setStatus(1);
						partitions.get(j).setIsFree(false);
						partitions.get(j).setRunningJobName(jobs.get(i).getName());
						partitions.get(j).setRunningJobSize(jobs.get(i).getSize());
						partitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
						partitions.get(j).setFrag();
						break;
					}
				}
			}
		}
	}
	
	
	/**
	 * Similar to firstFitAllocation
	 * sorts the partitions list from smallest to biggest size by calling sizeSort and saving output to new list
	 * runs same logic as first fit after sort
	 * after sort first fit logic works as best fit
	 * if list is sorted smallest to biggest the first place a job fits is the best fit
	 */
	public void bestFitAllocation() {
		sortedPartitions = sizeSort();
		for(int i = 0; i < jobs.size(); i++) {
			for(int j = 0; j < sortedPartitions.size(); j++) {
				if(sortedPartitions.get(j).isFree() && jobs.get(i).getStatus() == 0) {
					if(jobs.get(i).getSize() <= sortedPartitions.get(j).getSize()) {
						jobs.get(i).setStatus(1);
						sortedPartitions.get(j).setIsFree(false);
						sortedPartitions.get(j).setRunningJobName(jobs.get(i).getName());
						sortedPartitions.get(j).setRunningJobSize(jobs.get(i).getSize());
						sortedPartitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
						sortedPartitions.get(j).setFrag();
						break;
					}
				}
			}
		}
	}
	
	/**
	 * same method as best fit, uses reverse sorted list
	 */
	public void worstFitAllocation() {
		badSortedPartitions = badSizeSort();
		for(int i = 0; i < jobs.size(); i++) {
			for(int j = 0; j < badSortedPartitions.size(); j++) {
				if(badSortedPartitions.get(j).isFree() && jobs.get(i).getStatus() == 0) {
					if(jobs.get(i).getSize() <= badSortedPartitions.get(j).getSize()) {
						jobs.get(i).setStatus(1);
						badSortedPartitions.get(j).setIsFree(false);
						badSortedPartitions.get(j).setRunningJobName(jobs.get(i).getName());
						badSortedPartitions.get(j).setRunningJobSize(jobs.get(i).getSize());
						badSortedPartitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
						badSortedPartitions.get(j).setFrag();
						break;
					}
				}
			}
		}
	}
	
	/**
	 * jobs are allocated into the fixed partitions with the next fit algorithm
	 * once a job is allocated the next job will search for a partition in the indices after the previous allocation
	 * ex: if job 1 allocated to p2, job 2 will start searching for a place after p2 instead of looping through the whole list
	 */
	public void nextFitAllocation() {
		int lastAllocatedIndex = 0;
		for(int i = 0; i < jobs.size(); i++) {
			for(int j = lastAllocatedIndex; j < partitions.size(); j++) {
				if(partitions.get(j).isFree() && jobs.get(i).getStatus() == 0) {
					if(jobs.get(i).getSize() <= partitions.get(j).getSize()) {
						jobs.get(i).setStatus(1);
						partitions.get(j).setIsFree(false);
						partitions.get(j).setRunningJobName(jobs.get(i).getName());
						partitions.get(j).setRunningJobSize(jobs.get(i).getSize());
						partitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
						partitions.get(j).setFrag();
						lastAllocatedIndex = j;
						break;
					}
				}
			}
		}
	}
	
	/**
	 * @return printedJobs, the string that holds the all job info
	 */
	public String printJobs() {
		printedJobs = defaultJob;
		if(!jobs.isEmpty()) {
			for(int i = 0; i < jobs.size(); i++) {
				String temp = jobs.get(i).toString();
				printedJobs = printedJobs + "\n" + temp;
			}
		}
		return printedJobs;
	}
	
	/**
	 * @return printedPartitions, the string that holds all partition info when using first fit
	 */
	public String printPartitions_first() {
		printedPartitions = defaultP;
		if(!partitions.isEmpty()) {
			for(int i = 0; i < partitions.size(); i++) {
				String temp = partitions.get(i).toString();
				printedPartitions = printedPartitions + "\n" + temp;
			}
		}
		return printedPartitions;
	}
	
	/**
	 * @return printedPartitions, the string that holds all partition info when using best fit
	 */
	public String printPartitions_best() {
		printedPartitions = defaultP;
		if(!sortedPartitions.isEmpty()) {
			for(int i = 0; i < sortedPartitions.size(); i++) {
				String temp = sortedPartitions.get(i).toString();
				printedPartitions = printedPartitions + "\n" + temp;
			}
		}
		return printedPartitions;
	}
	
	/**
	 * @return printedPartitions, the string that holds all partition info when using worst fit
	 */
	public String printPartitions_worst() {
		printedPartitions = defaultP;
		if(!badSortedPartitions.isEmpty()) {
			for(int i = 0; i < badSortedPartitions.size(); i++) {
				String temp = badSortedPartitions.get(i).toString();
				printedPartitions = printedPartitions + "\n" + temp;
			}
		}
		return printedPartitions;
	}
	
}
