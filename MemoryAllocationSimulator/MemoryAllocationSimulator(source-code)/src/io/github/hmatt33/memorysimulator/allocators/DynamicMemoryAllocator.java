package io.github.hmatt33.memorysimulator.allocators;

import io.github.hmatt33.memorysimulator.memobjects.Job;
import io.github.hmatt33.memorysimulator.memobjects.Partition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * this class handles the logic of allocating jobs with dynamic partitions
 * @author Matt Hunt
 * @version 1.0
 */
public class DynamicMemoryAllocator {
	//lists
	private ArrayList<Job> jobs;
	private ArrayList<Partition> partitions;
	private ArrayList<Partition> sortedPartitions;
	private ArrayList<Partition> badSortedPartitions;
	private int memAddress = 0;
	
	//strings that will hold the allocation info
	String printedJobs = "";
	String printedPartitions = "";
	String printedSnapshot = "";
	
	//default display headers
	String defaultJob = "Job Name:" + "    " + "Job Size:" + "    " + "Job Status:"; 
	String defaultP = "P Name:" + "    " + "P Size:" + "    " + "Mem Addr:" + "   " + "Job:" + "    " + "Job Size:" + "    " + "Status:" + "    " + "Frag:";
	
	
	/**
	 * constructor
	 */
	public DynamicMemoryAllocator() {
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
	 * @return memAddress
	 */
	public int getMemAddress() {
		return memAddress;
	}
	
	/**
	 * @param memA, new memA
	 * set memA
	 */
	public void setMemAddress(int memA) {
		memAddress = memA;
	}
	
	/**
	 * @param jobName, name of job to end
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
		for(int i = 0; i < jobs.size(); i++) {
			if(jobs.get(i).getName().equals(jobName)) {
				jobs.get(i).setStatus(-1);
			}
		}
	}
	
	/**
	 * @param jobName, name of job to end for best fit
	 */
	public void endJobBest(String jobName) {
		for(int i = 0; i < sortedPartitions.size(); i++) {
			if(sortedPartitions.get(i).getRunningJobName().equals(jobName)) {
				sortedPartitions.get(i).setRunningJobStatus(-1);
				sortedPartitions.get(i).setRunningJobName("null");
				sortedPartitions.get(i).setRunningJobSize(0);
				sortedPartitions.get(i).setIsFree(true);
			}
		}
		for(int i = 0; i < jobs.size(); i++) {
			if(jobs.get(i).getName().equals(jobName)) {
				jobs.get(i).setStatus(-1);
			}
		}
	}
	
	/**
	 * @param jobName, name of job to end for worst fit
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
	 * clears all lists
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
	 * @return total amount of fragmentation
	 */
	public int totalFragmentation() {
		int total = 0;
		for(int i = 0; i < partitions.size(); i++) {
			//for dynamic memory the only fragmentation is the external fragmentation meaning memory not used
			//if the partition is free then it is external frag
			if(partitions.get(i).isFree()) {
				total = total + partitions.get(i).getFrag();
			}
		}
		return total;
	}
	
	/**
	 * @return total amount of fragmentation in best fit list
	 */
	public int totalFragmentationBest() {
		int total = 0;
		for(int i = 0; i < sortedPartitions.size(); i++) {
			//for dynamic memory the only fragmentation is the external fragmentation meaning memory not used
			//if the partition is free then it is external frag
			if(sortedPartitions.get(i).isFree()) {
				total = total + sortedPartitions.get(i).getFrag();
			}
		}
		return total;
	}
	
	/**
	 * @return total amount of fragmentation in worst fit list
	 */
	public int totalFragmentationWorst() {
		int total = 0;
		for(int i = 0; i < badSortedPartitions.size(); i++) {
			//for dynamic memory the only fragmentation is the external fragmentation meaning memory not used
			//if the partition is free then it is external frag
			if(badSortedPartitions.get(i).isFree()) {
				total = total + badSortedPartitions.get(i).getFrag();
			}
		}
		return total;
	}
	
	/**helper method to bestFitAllocation, sorts the array of partitions from smallest to largest size
	 * @param p
	 * @return the new sorted arraylist
	 */
	private ArrayList<Partition> sizeSort() {
		
		if(sortedPartitions == null) {
			sortedPartitions = (ArrayList<Partition>) partitions.clone();
		}
		
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
	 * @param p
	 * @return the new reverse sorted arraylist
	 */
	private ArrayList<Partition> badSizeSort() {
		if(badSortedPartitions == null) {
			badSortedPartitions = (ArrayList<Partition>) partitions.clone();
		}
		
		Collections.sort(badSortedPartitions, new Comparator<Partition>() {
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
		Collections.reverse(badSortedPartitions);
		return badSortedPartitions;
	}
	
	/**
	 * dynamic partitions version of first fit
	 * Allocates first jobs like normal
	 * when new jobs come in old ones finish partitions are resized as needed
	 */
	public void firstFitAllocation() {
		for(int i = 0; i < jobs.size(); i++) {
			for(int j = 0; j < partitions.size(); j++) {
				if(partitions.get(j).isFree() && jobs.get(i).getStatus() == 0) {
					if(jobs.get(i).getSize() <= partitions.get(j).getSize()) {
						if(jobs.get(i).getSize() < partitions.get(j).getSize()) {
							//this is the case where it does the extra resizing steps required by a dynamic memory
							jobs.get(i).setStatus(1);
							partitions.get(j).setIsFree(false);
							partitions.get(j).setRunningJobName(jobs.get(i).getName());
							partitions.get(j).setRunningJobSize(jobs.get(i).getSize());
							partitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
							//new partitions is created that has the size of the difference between job and previous partition
							int tempMem = partitions.get(j).getSize() - jobs.get(i).getSize();
							partitions.get(j).setMemAddress(tempMem);
							Partition newP = new Partition("newP", partitions.get(j).getSize() - jobs.get(i).getSize());
							newP.setMemAddress(tempMem + (partitions.get(j).getSize() - jobs.get(i).getSize()));
							newP.setFrag(newP.getSize());
							//new partition is added to partitions list right next to previous one
							partitions.add(j+1, newP);
							//old partition resized to fit new job
							partitions.get(j).setSize(jobs.get(i).getSize());
							partitions.get(j).setFrag(0);
							break;
						} else if(jobs.get(i).getSize() == partitions.get(j).getSize()) {
							//normal first fit like fixed partitions
							jobs.get(i).setStatus(1);
							partitions.get(j).setIsFree(false);
							partitions.get(j).setRunningJobName(jobs.get(i).getName());
							partitions.get(j).setRunningJobSize(jobs.get(i).getSize());
							partitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
							break;
						}
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
						if(jobs.get(i).getSize() < sortedPartitions.get(j).getSize()) {
							//this is the case where it does the extra resizing steps required by a dynamic memory
							jobs.get(i).setStatus(1);
							sortedPartitions.get(j).setIsFree(false);
							sortedPartitions.get(j).setRunningJobName(jobs.get(i).getName());
							sortedPartitions.get(j).setRunningJobSize(jobs.get(i).getSize());
							sortedPartitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
							//new partitions is created that has the size of the difference between job and previous partition
							int tempMem = sortedPartitions.get(j).getSize() - jobs.get(i).getSize();
							sortedPartitions.get(j).setMemAddress(tempMem);
							Partition newP = new Partition("newP", sortedPartitions.get(j).getSize() - jobs.get(i).getSize());
							newP.setMemAddress(tempMem + (sortedPartitions.get(j).getSize() - jobs.get(i).getSize()));
							newP.setFrag(newP.getSize());
							//new partition is added to partitions list right next to previous one
							sortedPartitions.add(j+1, newP);
							//old partition resized to fit new job
							sortedPartitions.get(j).setSize(jobs.get(i).getSize());
							sortedPartitions.get(j).setFrag(0);
							sizeSort();
							break;
						} else if(jobs.get(i).getSize() == sortedPartitions.get(j).getSize()) {
							//normal first fit like fixed partitions
							jobs.get(i).setStatus(1);
							sortedPartitions.get(j).setIsFree(false);
							sortedPartitions.get(j).setRunningJobName(jobs.get(i).getName());
							sortedPartitions.get(j).setRunningJobSize(jobs.get(i).getSize());
							sortedPartitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Worst fit algorithm for dynamic partitions
	 * jobs loop like normal
	 * partitions loop backwards in order to allow normal initialization
	 * dynamic resizing done once old jobs finish and new ones come in
	 */
	public void worstFitAllocation() {
		badSortedPartitions = badSizeSort();
		for(int i = 0; i < jobs.size(); i++) {
			for(int j = badSortedPartitions.size()-1; j >= 0; j--) {
				if(badSortedPartitions.get(j).isFree() && jobs.get(i).getStatus() == 0) {
					if(jobs.get(i).getSize() <= badSortedPartitions.get(j).getSize()) {
						if(jobs.get(i).getSize() < badSortedPartitions.get(j).getSize()) {
							//this is the case where it does the extra resizing steps required by a dynamic memory
							jobs.get(i).setStatus(1);
							badSortedPartitions.get(j).setIsFree(false);
							badSortedPartitions.get(j).setRunningJobName(jobs.get(i).getName());
							badSortedPartitions.get(j).setRunningJobSize(jobs.get(i).getSize());
							badSortedPartitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
							//new partitions is created that has the size of the difference between job and previous partition
							int tempMem = badSortedPartitions.get(j).getSize() - jobs.get(i).getSize();
							badSortedPartitions.get(j).setMemAddress(tempMem);
							Partition newP = new Partition("newP", badSortedPartitions.get(j).getSize() - jobs.get(i).getSize());
							newP.setMemAddress(tempMem + (badSortedPartitions.get(j).getSize() - jobs.get(i).getSize()));
							newP.setFrag(newP.getSize());
							//new partition is added to partitions list right next to previous one
							badSortedPartitions.add(j+1, newP);
							//old partition resized to fit new job
							badSortedPartitions.get(j).setSize(jobs.get(i).getSize());
							badSortedPartitions.get(j).setFrag(0);
							badSizeSort();
							break;
						} else if(jobs.get(i).getSize() == badSortedPartitions.get(j).getSize()) {
							//normal worst fit like fixed partitions
							jobs.get(i).setStatus(1);
							badSortedPartitions.get(j).setIsFree(false);
							badSortedPartitions.get(j).setRunningJobName(jobs.get(i).getName());
							badSortedPartitions.get(j).setRunningJobSize(jobs.get(i).getSize());
							badSortedPartitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * dynamic partitions version of next fit
	 * Allocates first jobs like normal
	 * when new jobs come in old ones finish partitions are resized as needed
	 */
	public void nextFitAllocation() {
		int lastAllocatedIndex = 0;
		for(int i = 0; i < jobs.size(); i++) {
			for(int j = lastAllocatedIndex; j < partitions.size(); j++) {
				if(partitions.get(j).isFree() && jobs.get(i).getStatus() == 0) {
					if(jobs.get(i).getSize() <= partitions.get(j).getSize()) {
						if(jobs.get(i).getSize() < partitions.get(j).getSize()) {
							//this is the case where it does the extra resizing steps required by a dynamic memory
							jobs.get(i).setStatus(1);
							partitions.get(j).setIsFree(false);
							partitions.get(j).setRunningJobName(jobs.get(i).getName());
							partitions.get(j).setRunningJobSize(jobs.get(i).getSize());
							partitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
							//new partitions is created that has the size of the difference between job and previous partition
							int tempMem = partitions.get(j).getSize() - jobs.get(i).getSize();
							partitions.get(j).setMemAddress(tempMem);
							Partition newP = new Partition("newP", partitions.get(j).getSize() - jobs.get(i).getSize());
							newP.setMemAddress(tempMem + (partitions.get(j).getSize() - jobs.get(i).getSize()));
							newP.setFrag(newP.getSize());
							//new partition is added to partitions list right next to previous one
							partitions.add(j+1, newP);
							//old partition resized to fit new job
							partitions.get(j).setSize(jobs.get(i).getSize());
							partitions.get(j).setFrag(0);
							lastAllocatedIndex = j;
							break;
						} else if(jobs.get(i).getSize() == partitions.get(j).getSize()) {
							jobs.get(i).setStatus(1);
							partitions.get(j).setIsFree(false);
							partitions.get(j).setRunningJobName(jobs.get(i).getName());
							partitions.get(j).setRunningJobSize(jobs.get(i).getSize());
							partitions.get(j).setRunningJobStatus(jobs.get(i).getStatus());
							lastAllocatedIndex = j;
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * @return String of job info
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
	 * @return string of partition info for first fit
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
	 * @return string of partition info for best fit
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
	 * @return string of partition info for worst fit
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
