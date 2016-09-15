/**
 * 
 * Steven Hullander
 * 3/22/2016
 * CPSC 2800
 * Section 0
 * 
 */
public class Lock
{
	//status that is atomic in nature
	private volatile boolean status;

	public Lock(boolean status)
	{
		this.status = status;
	}
	//current status
	public boolean status()
	{
		return status;
	}
    //opposite status
	public void invert()
	{
		status = !status;
	}
}