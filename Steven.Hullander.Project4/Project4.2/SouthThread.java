/**
 * 
 * Steven Hullander
 * 3/22/2016
 * CPSC 2800
 * Section 0
 * 
 */
public class SouthThread implements Runnable
{
	//initialize number of cars and lock
	private int cars;
	private Lock lock;
	public SouthThread(Lock lock, int cars)
	{
		this.lock = lock;
		this.cars = cars;
	}
	@Override
	public void run()
	{
		//check if thread is running(true)
		while (true)
		{
			synchronized (lock)
			{
				// Waits for North Bound thread to notify it that it can run
				try
				{
					while (!lock.status())
					{
						lock.wait();
					}
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}

				//while current car number is less then the total number of cars(4)
				for (int carNumber = 0; carNumber < cars; carNumber++)
				{
					System.out.print(Thread.currentThread().getName() + " car number " + (carNumber + 1) + " is crossing bridge. ");
					try
					{
						//time taken for car to pass over bridge (3 seconds)
						lock.wait(3000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " car number " + (carNumber + 1) + " has crossed bridge.");
				}
				//changes lock from current state to its opposite
				lock.invert();
				//lets waiting threads know that this thread is done
				lock.notifyAll();
			  
		   }

	  }
    }
}