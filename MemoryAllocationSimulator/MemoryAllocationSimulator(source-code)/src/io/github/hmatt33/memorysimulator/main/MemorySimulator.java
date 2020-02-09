package io.github.hmatt33.memorysimulator.main;

import io.github.hmatt33.memorysimulator.allocators.FixedMemoryAllocator;
import io.github.hmatt33.memorysimulator.allocators.DynamicMemoryAllocator;
import io.github.hmatt33.memorysimulator.ui.GUI;

/**Main class
 * creates simulation demos
 * tells GUI what to draw on screen
 * @author Matt Hunt
 * @version 1.0
 */
public class MemorySimulator {
	
	//allocators
	static FixedMemoryAllocator sim1 = new FixedMemoryAllocator();
	static DynamicMemoryAllocator sim2 = new DynamicMemoryAllocator();
	
	//total memory
	static int totalMemory = 1000;
	
	//strings used to hold results
	static String simType;
	static String simInfo;
	static String simInfo2;
	static String snapshotNum;
	static String header;
	static String j;
	static String p;
	static String frag;
	static String calc;
	static String tempCalc;
	
	//GUI
	static GUI ui = new GUI();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//all simulations
		
		//fixed partitions
		simulation1_firstFit();
		simulation1_firstFit2();
		simulation1_bestFit();
		simulation1_worstFit();
		simulation1_nextFit();
		
		//dynamic partitions
		simulation2_firstFit();
		simulation2_bestFit();
		simulation2_worstFit();
		simulation2_nextFit();
	}
	
	/**
	 * simulation that shows first fit with fixed partitions and all partitions same size
	 */
	private static void simulation1_firstFit() {
		
		sim1.addPartition("p1", 200);
		sim1.addPartition("p2", 200);
		sim1.addPartition("p3", 200);
		sim1.addPartition("p4", 200);
		sim1.addPartition("p5", 200);
		
		sim1.addJob("job 1", 100);
		sim1.addJob("job 2", 50);
		sim1.addJob("job 3", 50);
		sim1.addJob("job 4", 300);
		sim1.addJob("job 5", 200);
		sim1.addJob("job 6", 75);
		sim1.addJob("job 7", 200);
		
		simType = "Fixed Partition";
		simInfo = "\n" + totalMemory + " total memory";
		simInfo2 = "\n" + "Simulation 1: " + "\n";
		header = simType + simInfo + simInfo2;
		
		sim1.firstFitAllocation();
		
		snapshotNum = "Snapshot 1: First Fit, all partitions same size" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_first();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = header + "\n" + calc;
		
		sim1.endJob("job 2");
		sim1.firstFitAllocation();
		
		snapshotNum = "\n" +"Snapshot 2: First Fit, all partitions same size" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_first();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = tempCalc + "\n" + snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		
		ui.drawSim1F(calc);
	}
	
	/**
	 * simulation that shows first fit with fixed partitions and all partitions different sizes
	 */
	private static void simulation1_firstFit2() {
		sim1.clearLists();
		
		sim1.addPartition("p1", 400);
		sim1.addPartition("p2", 100);
		sim1.addPartition("p3", 50);
		sim1.addPartition("p4", 200);
		sim1.addPartition("p5", 250);
		
		sim1.addJob("job 1", 100);
		sim1.addJob("job 2", 50);
		sim1.addJob("job 3", 50);
		sim1.addJob("job 4", 300);
		sim1.addJob("job 5", 200);
		sim1.addJob("job 6", 75);
		sim1.addJob("job 7", 200);
		
		sim1.firstFitAllocation();
		
		snapshotNum = "Snapshot 1: First Fit, all partitions different sizes" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_first();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = calc;
		
		sim1.endJob("job 3");
		sim1.endJob("job 5");
		sim1.firstFitAllocation();
		
		snapshotNum = "\n" + "Snapshot 2: First Fit, all partitions different sizes" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_first();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = tempCalc + "\n" + snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		
		ui.drawSim1F2(calc);
	}
	
	/**
	 * simulation that shows best fit with fixed partitions and all partitions different sizes
	 */
	private static void simulation1_bestFit() {
		sim1.clearLists();
		
		sim1.addPartition("p1", 400);
		sim1.addPartition("p2", 100);
		sim1.addPartition("p3", 50);
		sim1.addPartition("p4", 200);
		sim1.addPartition("p5", 250);
		
		sim1.addJob("job 1", 100);
		sim1.addJob("job 2", 50);
		sim1.addJob("job 3", 50);
		sim1.addJob("job 4", 300);
		sim1.addJob("job 5", 200);
		sim1.addJob("job 6", 75);
		sim1.addJob("job 7", 200);
		
		sim1.bestFitAllocation();
		
		snapshotNum = "Snapshot 1: Best Fit" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_best();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = calc;
		
		sim1.endJobBest("job 2");
		sim1.endJobBest("job 3");
		sim1.bestFitAllocation();
		
		snapshotNum = "\n" + "Snapshot 2: Best Fit" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_best();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = tempCalc + "\n" + snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		
		ui.drawSim1B(calc);
		
	}
	
	/**
	 * simulation that shows worst fit with fixed partitions and all partitions different sizes
	 */
	private static void simulation1_worstFit() {
		sim1.clearLists();
		
		sim1.addPartition("p1", 400);
		sim1.addPartition("p2", 100);
		sim1.addPartition("p3", 50);
		sim1.addPartition("p4", 200);
		sim1.addPartition("p5", 250);
		
		sim1.addJob("job 1", 100);
		sim1.addJob("job 2", 50);
		sim1.addJob("job 3", 50);
		sim1.addJob("job 4", 300);
		sim1.addJob("job 5", 200);
		sim1.addJob("job 6", 75);
		sim1.addJob("job 7", 200);
		
		sim1.worstFitAllocation();
		
		snapshotNum = "Snapshot 1: Worst Fit" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_worst();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = calc;
		
		sim1.endJobWorst("job 3");
		sim1.endJob("job 1");
		sim1.worstFitAllocation();
		
		snapshotNum = "\n" + "Snapshot 2: Worst Fit" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_worst();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = tempCalc + "\n" + snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		
		ui.drawSim1W(calc);
	}

	/**
	 * simulation that shows next fit with fixed partitions and all partitions different sizes
	 */
	private static void simulation1_nextFit() {
		sim1.clearLists();
		
		sim1.addPartition("p1", 30);
		sim1.addPartition("p2", 50);
		sim1.addPartition("p3", 10);
		
		sim1.addJob("job 1", 50);
		sim1.addJob("job 2", 10);
		
		sim1.nextFitAllocation();
		
		snapshotNum = "Snapshot 1: Next Fit" + "\n";
		j = sim1.printJobs();
		p = sim1.printPartitions_first();
		frag = "Total Internal Fragmentation: " + sim1.totalFragmentation();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = calc;
		
		ui.drawSim1N(calc);
	}
	
	/**
	 * simulation that shows first fit with dynamic partitions
	 */
	private static void simulation2_firstFit() {
		sim2.addJob("job 1", 100);
		sim2.addPartition("p1", 100);
		
		sim2.addJob("job 2", 500);
		sim2.addPartition("p2", 500);
		
		sim2.addJob("job 3", 200);
		sim2.addPartition("p3", 200);
		
		sim2.addJob("job 4", 50);
		sim2.addPartition("p4", 50);
		
		sim2.addJob("job 5", 150);
		sim2.addPartition("p5", 150);
		
		sim2.addJob("job 6", 75);
		sim2.addJob("job 7", 200);
		
		simType = "Dynamic Partition";
		simInfo = "\n" + totalMemory + " total memory";
		simInfo2 = "\n" + "Simulation 2: " + "\n";
		header = simType + simInfo + simInfo2;
		
		sim2.firstFitAllocation();
		
		snapshotNum = "Snapshot 1: First Fit" + "\n";
		j = sim2.printJobs();
		p = sim2.printPartitions_first();
		frag = "Total External Fragmentation: " + sim2.totalFragmentation();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = header + "\n" + calc;
		
		sim2.endJob("job 2");
		sim2.firstFitAllocation();
		
		snapshotNum = "\n" +"Snapshot 2: First Fit" + "\n";
		j = sim2.printJobs();
		p = sim2.printPartitions_first();
		frag = "Total External Fragmentation: " + sim2.totalFragmentation();
		calc = tempCalc + "\n" + snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		
		ui.drawSim2F(calc);
	}
	
	/**
	 * simulation that shows best fit with dynamic partitions
	 */
	private static void simulation2_bestFit() {
		sim2.clearLists();
		
		sim2.addJob("job 1", 100);
		sim2.addPartition("p1", 100);
		
		sim2.addJob("job 2", 500);
		sim2.addPartition("p2", 500);
		
		sim2.addJob("job 3", 200);
		sim2.addPartition("p3", 200);
		
		sim2.addJob("job 4", 50);
		sim2.addPartition("p4", 50);
		
		sim2.addJob("job 5", 150);
		sim2.addPartition("p5", 150);
		
		sim2.addJob("job 6", 75);
		sim2.addJob("job 7", 200);
		
		sim2.bestFitAllocation();
		
		snapshotNum = "Snapshot 1: Best Fit" + "\n";
		j = sim2.printJobs();
		p = sim2.printPartitions_best();
		frag = "Total External Fragmentation: " + sim2.totalFragmentationBest();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = "\n" + calc;
		
		sim2.endJobBest("job 2");
		sim2.bestFitAllocation();
		
		snapshotNum = "\n" +"Snapshot 2: Best Fit" + "\n";
		j = sim2.printJobs();
		p = sim2.printPartitions_best();
		frag = "Total External Fragmentation: " + sim2.totalFragmentationBest();
		calc = tempCalc + "\n" + snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		
		ui.drawSim2B(calc);
	}
	
	/**
	 * simulation that shows worst fit with dynamic partitions
	 */
	private static void simulation2_worstFit() {
		sim2.clearLists();
		
		sim2.addJob("job 1", 100);
		sim2.addPartition("p1", 100);
		
		sim2.addJob("job 2", 500);
		sim2.addPartition("p2", 500);
		
		sim2.addJob("job 3", 200);
		sim2.addPartition("p3", 200);
		
		sim2.addJob("job 4", 50);
		sim2.addPartition("p4", 50);
		
		sim2.addJob("job 5", 150);
		sim2.addPartition("p5", 150);
		
		sim2.addJob("job 6", 75);
		sim2.addJob("job 7", 200);
		
		sim2.worstFitAllocation();
		
		snapshotNum = "Snapshot 1: Worst Fit" + "\n";
		j = sim2.printJobs();
		p = sim2.printPartitions_worst();
		frag = "Total External Fragmentation: " + sim2.totalFragmentationWorst();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = "\n" + calc;
		
		sim2.endJobWorst("job 2");
		sim2.worstFitAllocation();
		
		snapshotNum = "\n" +"Snapshot 2: Worst Fit" + "\n";
		j = sim2.printJobs();
		p = sim2.printPartitions_worst();
		frag = "Total External Fragmentation: " + sim2.totalFragmentationWorst();
		calc = tempCalc + "\n" + snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		
		ui.drawSim2W(calc);
	}
	
	/**
	 * simulation that shows next fit with dynamic partitions
	 */
	private static void simulation2_nextFit() {
		sim2.clearLists();
		
		sim2.addJob("job 1", 30);
		sim2.addPartition("p1", 30);
		sim2.addJob("job 2", 50);
		sim2.addPartition("p2", 50);
		sim2.addJob("job 3", 20);
		sim2.addPartition("p3", 20);
		
		sim2.nextFitAllocation();
		
		snapshotNum = "Snapshot 1: Next Fit" + "\n";
		j = sim2.printJobs();
		p = sim2.printPartitions_first();
		frag = "Total External Fragmentation: " + sim2.totalFragmentation();
		calc = snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		tempCalc = calc;
		
		sim2.endJob("job 1");
		sim2.endJob("job 2");
		sim2.endJob("job 3");
		
		sim2.addJob("job 4", 40);
		sim2.addJob("job 5", 20);
		
		sim2.nextFitAllocation();
		
		snapshotNum = "\n" +"Snapshot 2: Next Fit" + "\n";
		j = sim2.printJobs();
		p = sim2.printPartitions_first();
		frag = "Total External Fragmentation: " + sim2.totalFragmentation();
		calc = tempCalc + "\n" + snapshotNum + j + "\n" + "\n" + p + "\n" + frag;
		
		ui.drawSim2N(calc);
	}

}
