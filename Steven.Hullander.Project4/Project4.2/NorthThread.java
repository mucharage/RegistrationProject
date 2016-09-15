/**
 * 
 * Steven Hullander
 * 3/22/2016
 * CPSC 2800
 * Section 0
 * 
 */
public class NorthThread implements Runnable
{
	//initialize number of cars and lock
	private int cars;
	private Lock lock;
	public NorthThread(Lock lock, int cars)
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
				//North bound cars go first
				while (!lock.status())
				{
					//while current car number is less then the total number of cars(4)
					for (int carCounter = 0; carCounter < cars; carCounter++)
					{
						System.out.print(Thread.currentThread().getName() + " car number " + (carCounter+1) + " is crossing bridge. ");
						try
						{
							//time taken for car to pass over bridge (3 seconds)
							lock.wait(3000);

						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " car number " + (carCounter+1) + " has crossed bridge.");

					}
					//lets waiting threads know that this thread is done
					lock.notifyAll();
					//changes lock from current state to its opposite
					lock.invert();
				}
				try
				{
					// Waits until notifyAll() from SouthThread
					lock.wait();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}