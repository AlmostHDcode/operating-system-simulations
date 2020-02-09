package io.github.hmatt33.sleepingbarber;

/** barber class that defines barber object and behavior
 * @author Matt Hunt
 * @version 1.0
 */
public class Barber implements Runnable{
	BarberShop shop;
	
	/** constructor
	 * @param shop
	 */
	public Barber(BarberShop shop) {
		this.shop = shop;
	}
	
	/* 
	 * run method that does the barber check method once thread starts
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//executes at start
		System.out.println("Barber is in the barber shop");
		
		//infinite loop to simulate a continuous stream of customers coming to the shop
		while(true) {
			//barber checks to see if there are customers in shop to cut hair
			//otherwise barber goes to sleep
			shop.barberCheck();
		}
	}
}
