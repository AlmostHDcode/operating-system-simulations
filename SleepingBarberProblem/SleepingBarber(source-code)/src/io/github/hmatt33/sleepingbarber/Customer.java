package io.github.hmatt33.sleepingbarber;

import java.util.Date;

/** Customer class that defines Customer object and customer check behavior
 * @author Matt Hunt
 * @version 1.0
 */
public class Customer implements Runnable{
	private String name;
	private Date arrivalTime;
	BarberShop shop;
	
	/** constructor
	 * @param shop
	 */
	public Customer(BarberShop shop) {
		this.shop = shop;
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
	 * @return the arrivalTime
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/*
	 * run method that runs the customer check method
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//customer goes into shop
		goToShop();
	}
	
	/**
	 * customer check method
	 * customer checks if barber is ready or if there are chairs available to sit in
	 */
	private synchronized void goToShop() {
		shop.customerCheck(this);
	}
}
