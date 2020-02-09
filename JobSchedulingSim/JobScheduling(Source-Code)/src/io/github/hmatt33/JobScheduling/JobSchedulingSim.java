package io.github.hmatt33.JobScheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**Main class, allows user to select and run the different algorithms
 * @author Matt Hunt
 * @version 1.0
 */
public class JobSchedulingSim {

	//fields
	
	//list that holds all jobs
	private static ArrayList<Job> jobs;
	//number of jobs
	private static float jobNum = 0;
	//helper variable that helps with setting job finish time
	private static int ft = 0;
	//helper variable that helps with setting job turnaround time
	private static int turn = 0;
	//helper variable that helps with setting job wait time
	private static int wt = 0;
	//helps calculate total turnaround of all jobs
	private static int totalturn = 0;
	//helps calculate total wait of all jobs
	private static int totalwt = 0;
	//average wait time
	private static float avgWait = 0;
	//average turnaround
	private static float avgTurnaround = 0;
	//scanner object that takes user input
	private static Scanner scan = new Scanner(System.in);

	/**Main class, allows user to select and run the different algorithms
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		boolean quit = false;

		System.out.println("Welcome to the Job Scheduling Simulator" + "\n");

		//while loop makes this repeat until user wants to exit
		while(!quit) {
			//user selects the algorithm
			System.out.println("Please enter what algorithm you wish to use, enter \"q\" to quit");
			System.out.println("Enter \"fcfs\", \"sjn\", \"srt\", \"rr\", or \"q\":");
			String x = scan.next();

			//switch between different commands that user could type
			switch(x) {
			case "fcfs": {
				//run first come first serve
				fcfs();
				break;
			}
			case "sjn": {
				//run shortest job next
				sjn();
				break;
			}
			case "srt": {
				//run shortest remaining time
				srt();
				break;
			}
			case "rr": {
				//run round robin
				roundRobin();
				break;
			}
			case "q": {
				//exits system
				System.out.println("Now exiting the system");
				quit = true;
				break;
			}
			default: {
				//default condition is no other case happens
				System.out.println(x + " is not a valid command. Please try again");
				break;
			}
			}//end of switch
		}//end of while loop

		scan.close();

	}
	
	/**
	 * helper method that sorts the job list by arrival time
	 * used in fcfs algorithm
	 * used to format result table
	 */
	private static void arrivalSort() {

		//define new sort method with a comparator
		Collections.sort(jobs, new Comparator<Job>() {
			@Override
			public int compare(Job j1, Job j2) {
				// TODO Auto-generated method stub
				//if job 1 arrives after return 1
				if(j1.getArrival() > j2.getArrival()) {
					return 1;
				//if job 1 arrives sooner return -1
				} else if(j1.getArrival() < j2.getArrival()) {
					return -1;
				} else { // else they are equal return 0
					return 0;
				}
			}

		});
	}

	/**
	 * fcfs algorthim
	 * takes user input
	 * sorts list of jobs by arrival time
	 * calculates wait time, turnaround time, average wait, average turnaround
	 */
	private static void fcfs() {
		ft = 0;
		turn = 0;
		wt = 0;
		totalturn = 0;
		totalwt = 0;
		avgWait = 0;
		avgTurnaround = 0;
		scan = new Scanner(System.in);
		jobs = new ArrayList<Job>();

		//user input setup
		System.out.println("Enter how many jobs you want:");
		jobNum = scan.nextFloat();

		//user input of job info
		for(int i = 0; i < jobNum; i++) {
			System.out.println("Enter the name of Job: " + (i + 1));
			String x = scan.next();
			System.out.println("Enter the arrival time for Job: " + x);
			int y = scan.nextInt();
			System.out.println("Enter the cpu cycle of Job: " + x);
			int z = scan.nextInt();

			Job j = new Job(x, y, z);
			jobs.add(j);
		}

		//sort list by arrival time
		arrivalSort();

		for(int i = 0; i < jobNum; i++) {
			//if for loop is looking past the first entry
			if(i > 0) {
				//if arrival of job is greater than the last jobs finish time
				if(jobs.get(i).getArrival() > jobs.get(i-1).getFinishTime()) {
					ft = jobs.get(i).getArrival() + jobs.get(i).getCycle();
					jobs.get(i).setFinishTime(ft);
					turn = jobs.get(i).getFinishTime() - jobs.get(i).getArrival();
					jobs.get(i).setTurnaround(turn);
					wt = jobs.get(i).getTurnaround() - jobs.get(i).getCycle();
					jobs.get(i).setWaitTime(wt);
					totalturn = totalturn + jobs.get(i).getTurnaround();
					totalwt = totalwt + jobs.get(i).getWaitTime();
				} else { //if arrival is not greater than last jobs finish time
					ft = jobs.get(i-1).getFinishTime() + jobs.get(i).getCycle();
					jobs.get(i).setFinishTime(ft);
					turn = jobs.get(i).getFinishTime() - jobs.get(i).getArrival();
					jobs.get(i).setTurnaround(turn);
					wt = jobs.get(i).getTurnaround() - jobs.get(i).getCycle();
					jobs.get(i).setWaitTime(wt);
					totalturn = totalturn + jobs.get(i).getTurnaround();
					totalwt = totalwt + jobs.get(i).getWaitTime();
				}
			} else { //i=0 it is the first entry
				ft = jobs.get(i).getArrival() + jobs.get(i).getCycle();
				jobs.get(i).setFinishTime(ft);
				turn = jobs.get(i).getFinishTime() - jobs.get(i).getArrival();
				jobs.get(i).setTurnaround(turn);
				wt = jobs.get(i).getTurnaround() - jobs.get(i).getCycle();
				jobs.get(i).setWaitTime(wt);
				totalturn = totalturn + jobs.get(i).getTurnaround();
				totalwt = totalwt + jobs.get(i).getWaitTime();
			}
		}
		//calculate averages
		avgWait = totalwt / jobNum;
		avgTurnaround = totalturn / jobNum;

		//priting results of algorithm as table
		System.out.println("Job Name: " + "\t" + "Arrival Time: " + "\t" + "CPU Cycle: " + "\t" + "Finish Time: " + "\t" + "Wait Time: " + "\t" + "Turnaround:");
		for(int i = 0; i < jobs.size(); i++) {
			System.out.println(jobs.get(i).toString());
		}
		System.out.println("Average Wait Time: " + avgWait);
		System.out.println("Average Turnaround Time: " + avgTurnaround + "\n");

	}

	/**
	 * sjn algorithm
	 */
	private static void sjn() {
		ft = 0;
		turn = 0;
		wt = 0;
		totalturn = 0;
		totalwt = 0;
		avgWait = 0;
		avgTurnaround = 0;
		scan = new Scanner(System.in);
		jobs = new ArrayList<Job>();
		int num = 0;
		int time = 0;

		//user input setup
		System.out.println("Enter how many jobs you want:");
		jobNum = scan.nextFloat();

		//user enter job info
		for(int i = 0; i < jobNum; i++) {
			System.out.println("Enter the name of Job: " + (i + 1));
			String x = scan.next();
			System.out.println("Enter the arrival time for Job: " + x);
			int y = scan.nextInt();
			System.out.println("Enter the cpu cycle of Job: " + x);
			int z = scan.nextInt();

			Job j = new Job(x, y, z);
			jobs.add(j);
		}
		
		//while number of completed jobs is not equal to number of jobs
		while(num != jobNum) {
			int min = 1000; //very high num just to make sure values are always below it when starting
			int count = (int) jobNum;
			
			//find minimum cpu cycle
			for(int i = 0; i < jobNum; i++) {
				if(jobs.get(i).getArrival() <= time && !jobs.get(i).isDone() && jobs.get(i).getCycle() < min) {
					min = jobs.get(i).getCycle();
					count = i;
				}
			}
			
			//increment the time
			if(count == jobNum) {
				time++;
				
			} else { // job finishes and results set
				ft = time + jobs.get(count).getCycle();
				jobs.get(count).setFinishTime(ft);
				time = time + jobs.get(count).getCycle();
				turn = jobs.get(count).getFinishTime() - jobs.get(count).getArrival();
				jobs.get(count).setTurnaround(turn);
				wt = jobs.get(count).getTurnaround() - jobs.get(count).getCycle();
				jobs.get(count).setWaitTime(wt);
				jobs.get(count).setDone(true);
				num++;
				totalturn = totalturn + jobs.get(count).getTurnaround();
				totalwt = totalwt + jobs.get(count).getWaitTime();
			}
			
		}
		
		//calc averages
		avgWait = totalwt / jobNum;
		avgTurnaround = totalturn / jobNum;
		
		arrivalSort();
		
		//print results
		System.out.println("Job Name: " + "\t" + "Arrival Time: " + "\t" + "CPU Cycle: " + "\t" + "Finish Time: " + "\t" + "Wait Time: " + "\t" + "Turnaround:");
		for(int i = 0; i < jobs.size(); i++) {
			System.out.println(jobs.get(i).toString());
		}
		System.out.println("Average Wait Time: " + avgWait);
		System.out.println("Average Turnaround Time: " + avgTurnaround + "\n");
		
	}

	/**
	 * srt algorithm
	 */
	private static void srt() {
		turn = 0;
		wt = 0;
		totalturn = 0;
		totalwt = 0;
		avgWait = 0;
		avgTurnaround = 0;
		scan = new Scanner(System.in);
		jobs = new ArrayList<Job>();
		int num = 0;
		int time = 0;
		//list that will be used as copy of cpu cyles of all jobs
		ArrayList<Integer> tempCycles = new ArrayList<Integer>();

		//setup
		System.out.println("Enter how many jobs you want:");
		jobNum = scan.nextFloat();

		for(int i = 0; i < jobNum; i++) {
			System.out.println("Enter the name of Job: " + (i + 1));
			String x = scan.next();
			System.out.println("Enter the arrival time for Job: " + x);
			int y = scan.nextInt();
			System.out.println("Enter the cpu cycle of Job: " + x);
			int z = scan.nextInt();

			Job j = new Job(x, y, z);
			jobs.add(j);
		}

		//copy all cpu cycles of jobs into temp list
		for(int i = 0; i < jobs.size(); i++) {
			tempCycles.add(jobs.get(i).getCycle());
		}

		//same structure of sjn
		while(num != jobNum) {
			int min = 1000; //very high num just to make sure values are always below it when starting
			int count = (int) jobNum;

			for(int i = 0; i < jobNum; i++) {
				if(jobs.get(i).getArrival() <= time && !jobs.get(i).isDone() && tempCycles.get(i) < min) {
					min = tempCycles.get(i);
					count = i;
				}
			}

			if(count == jobNum) {
				time++;
			} else {
				tempCycles.set(count, (tempCycles.get(count) - 1));
				time++;
				if(tempCycles.get(count) == 0) {
					jobs.get(count).setFinishTime(time);
					turn = jobs.get(count).getFinishTime() - jobs.get(count).getArrival();
					jobs.get(count).setTurnaround(turn);
					wt = jobs.get(count).getTurnaround() - jobs.get(count).getCycle();
					jobs.get(count).setWaitTime(wt);
					totalturn = totalturn + jobs.get(count).getTurnaround();
					totalwt = totalwt + jobs.get(count).getWaitTime();
					jobs.get(count).setDone(true);
					num++;
				}
			}
		}
		avgWait = totalwt / jobNum;
		avgTurnaround = totalturn / jobNum;

		arrivalSort();
		
		System.out.println("Job Name: " + "\t" + "Arrival Time: " + "\t" + "CPU Cycle: " + "\t" + "Finish Time: " + "\t" + "Wait Time: " + "\t" + "Turnaround:");
		for(int i = 0; i < jobs.size(); i++) {
			System.out.println(jobs.get(i).toString());
		}
		System.out.println("Average Wait Time: " + avgWait);
		System.out.println("Average Turnaround Time: " + avgTurnaround + "\n");
	}

	/**
	 * round robin algorithm
	 */
	private static void roundRobin() {
		ft = 0;
		turn = 0;
		wt = 0;
		totalturn = 0;
		totalwt = 0;
		avgWait = 0;
		avgTurnaround = 0;
		scan = new Scanner(System.in);
		jobs = new ArrayList<Job>();
		//temp list needed again for cpu cycles
		ArrayList<Integer> tempCycles = new ArrayList<Integer>();
		int num = 0;
		//time quantum
		int q = 0;

		//setup
		System.out.println("Enter how many jobs you want:");
		jobNum = scan.nextFloat();
		
		System.out.println("Enter the time quantum:");
		q = scan.nextInt();

		for(int i = 0; i < jobNum; i++) {
			System.out.println("Enter the name of Job: " + (i + 1));
			String x = scan.next();
			System.out.println("Enter the arrival time for Job: " + x);
			int y = scan.nextInt();
			System.out.println("Enter the cpu cycle of Job: " + x);
			int z = scan.nextInt();

			Job j = new Job(x, y, z);
			jobs.add(j);
		}
		
		//temp list setup
		for(int i = 0; i < jobs.size(); i++) {
			tempCycles.add(jobs.get(i).getCycle());
		}
		
		do {
			for(int i = 0; i < jobNum; i++) {
				//if cycle is more than the time quantum
				if(tempCycles.get(i) > q) {
					//subtract quantum from total cycle
					tempCycles.set(i, (tempCycles.get(i) - q));
					for(int j = 0; j < jobNum; j++) {
						//if the cycle isnt zero
						if(j != i && tempCycles.get(j) != 0) {
							//calc wait time
							jobs.get(j).setWaitTime(jobs.get(j).getWaitTime() + q);
						}
					}
				} else { // if cycle is less than quantum
					for(int j = 0; j < jobNum; j++) {
						if(j != i && tempCycles.get(j) != 0) {
							//calc wait, wait + cycle instead of wait + quantum
							jobs.get(j).setWaitTime(jobs.get(j).getWaitTime() + tempCycles.get(i));
						}
					}
					tempCycles.set(i, 0);
				}
			}
			num = 0;
			for(int i = 0; i < jobNum; i++) {
				num = num + tempCycles.get(i);
			}
		}
		while(num != 0);
		//calc results
		for(int i = 0; i < jobNum; i++) {
			ft = jobs.get(i).getWaitTime() + jobs.get(i).getCycle();
			jobs.get(i).setFinishTime(ft);
			turn = jobs.get(i).getFinishTime() - jobs.get(i).getArrival();
			jobs.get(i).setTurnaround(turn);
			totalturn = totalturn + jobs.get(i).getTurnaround();
			totalwt = totalwt + jobs.get(i).getWaitTime();
		}
		
		//calc averages
		avgWait = totalwt / jobNum;
		avgTurnaround = totalturn / jobNum;

		arrivalSort();
		
		//print results
		System.out.println("Job Name: " + "\t" + "Arrival Time: " + "\t" + "CPU Cycle: " + "\t" + "Finish Time: " + "\t" + "Wait Time: " + "\t" + "Turnaround:");
		for(int i = 0; i < jobs.size(); i++) {
			System.out.println(jobs.get(i).toString());
		}
		System.out.println("Average Wait Time: " + avgWait);
		System.out.println("Average Turnaround Time: " + avgTurnaround + "\n");
		
	}

}
