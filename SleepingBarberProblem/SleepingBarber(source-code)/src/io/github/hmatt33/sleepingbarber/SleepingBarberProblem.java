package io.github.hmatt33.sleepingbarber;

/** main class of Sleeping Barber assignment
 * @author Matt Hunt
 * @version 1.0
 */
public class SleepingBarberProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create the shop
		BarberShop shop = new BarberShop();
		//create the barber
		Barber b = new Barber(shop);
		//create the stream of customers
		CustomerStream custStream = new CustomerStream(shop);
		
		//create a thread for the barber and the customer stream
		Thread barberThread = new Thread(b);
		Thread custStreamThread = new Thread(custStream);
		
		//start the threads
		barberThread.start();
		custStreamThread.start();
	}

}
