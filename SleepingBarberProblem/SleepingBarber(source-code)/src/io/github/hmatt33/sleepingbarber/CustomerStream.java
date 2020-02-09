package io.github.hmatt33.sleepingbarber;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Matt Hunt
 * @version 1.0
 */
public class CustomerStream implements Runnable{
	BarberShop shop;
	Customer c;
	
	//how long the thread will sleep
	long sleepTime;
	
	/** constructor
	 * @param shop
	 */
	public CustomerStream(BarberShop shop) {
		this.shop = shop;
	}

	/*
	 * creates a stream of customers that come into the shop
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//infinite loop to simulate a continuous stream of customers
		while(true) {
			//new customer created
			c = new Customer(shop);
			//new thread created for each customer
			Thread customerThread = new Thread(c);
			//name set to the thread id
			c.setName("" + customerThread.getId());
			//arrival time of customer
			c.setArrivalTime(new Date());
			//thread started
			customerThread.start();
			
			//sleep time is between 1 and 20
			sleepTime = (long) Math.ceil(Math.random() * 20);
			
			try {
				TimeUnit.SECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
