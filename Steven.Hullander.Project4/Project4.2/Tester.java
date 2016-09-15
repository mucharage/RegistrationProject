/**
 * 
 * Steven Hullander
 * 3/22/2016
 * CPSC 2800
 * Section 0
 * 
 */
public class Tester
{
	public static void main(String[] args)
	{
		//run example with 4 cars
		int numberOfCars = 4;
		//create Lock object (false means unlocked)
		Lock lock = new Lock(false);
		//create two threads (North and South) 
		Thread northThread = new Thread(new NorthThread(lock, numberOfCars), "North bound");
		Thread southThread = new Thread(new SouthThread(lock, numberOfCars), "South bound");
		northThread.start();
		southThread.start();
	}
}