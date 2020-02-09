package io.github.hmatt33.sleepingbarber;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/** Barber shop class that holds the barber and customer checks
 * @author Matt Hunt
 * @version 1.0
 */
public class BarberShop {
	//amount of chairs in shop
	int chairs;
	//list of customers
	LinkedList<Customer> customers;
	
	/**
	 * constructor 
	 */
	public BarberShop() {
		chairs = 5;
		customers = new LinkedList<Customer>();
	}

	/**
	 * behavior of barber
	 * if no customers barber goes to sleep
	 * if there is a customer barber cuts hair
	 */
	public void barberCheck() {
		Customer c;
		long cutTime = 0;
		
		synchronized(customers) {
			while(customers.size() == 0) {
				System.out.println("Barber sees no customers, barber goes to sleep");
				
				try {
					customers.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("Barber sees a customer");
			c = customers.poll();
		}
		
		try {
			System.out.println("Barber is cutting Customer " + c.getName() + "'s hair");
			cutTime = (long) Math.ceil(Math.random() * 20);
			TimeUnit.SECONDS.sleep(cutTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Customer " + c.getName() + "'s hair cut is done");
		System.out.println("hair cut took: " + cutTime + " seconds");
	}
	
	/**
	 * @param c, the customer that comes in
	 * if customer comes in and barber is asleep, barber cuts hair
	 * if barber awake then there is someone already getting cut
	 * if chairs are available customer sits down
	 * if no chairs left customer leaves
	 */
	public void customerCheck(Customer c) {
		int remainingChairs;
		System.out.println("Customer " + c.getName() + " comes into the barber shop at " + c.getArrivalTime());
		
		synchronized(customers) {
			if(customers.size() == chairs) {
				System.out.println("There are no chairs left");
				System.out.println("Customer " + c.getName() + " leaves the shop");
				return;
			}
			System.out.println("Customer " + c.getName() + " sat in one of the chairs");
			customers.offer(c);
			remainingChairs = chairs - customers.size();
			System.out.println("There are " + remainingChairs + " chairs remaining");
			
			if(customers.size() == 1) {
				customers.notify();
			}
		}
	}
}
