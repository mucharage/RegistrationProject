/**
 * 
 * Steven Hullander
 * 3/22/2016
 * CPSC 2800
 * Section 0
 * 
 */
import java.util.Scanner;
public class PageFileTester
{
	public static void main(String[] args)
	{
		//Create new Scanner object
		Scanner in = new Scanner(System.in);
		//variable declaration/initialization
		final double ADDRESS_MAX = 4294967295.0;
		int offset = 0;
		int virtAddress = 0;
		double virtPage = 0;
		int systemPage = 0;
		//prompt for page size
		System.out.println("Please enter the system page size:");
		//if int is entered, update systemPage
		if (in.hasNextInt())
		{
			systemPage = in.nextInt();
		}
		if (isPowerOfTwo(systemPage))
		{
			//prompt for virtual address
			System.out.println("Please enter the virtual address:");
			if (in.hasNextInt())
			{
				virtAddress = in.nextInt();
			}
			//Check and see if 32-Bit
			if (virtAddress>0 && virtAddress<ADDRESS_MAX)
				
			{
				// virtPage is calculated as (log base 2 of systemPage) 1:
				virtPage = (Math.log(systemPage)/Math.log(2))-1;
				
				// Offset is remainder of virtAdress divided by systemPage:
				offset = virtAddress % systemPage;
				
				// Outputs the virtPage and Offset:
				System.out.println("This address is in virtual page: " + "\n" + (int) virtPage);
				System.out.println("At offset: " + "\n" + offset);
			}
			else
			{
				System.out.println("The Virtual Address size must be between 0 and 2^32 - 1!");
				//rerun entire program
				PageFileTester.main(args);
			}
		}
		else
		{
			System.out.println("This system input page is not allowed. Please try again");
			//rerun entire program
			PageFileTester.main(args);
		}
	}
	//test to see if systemPage is a power of two
	static boolean isPowerOfTwo(int testNum)
	{
		//set ret to false
		boolean ret = false;
		for (int i = 9; i < 15 || ret==false; i++)
		{
			//if 2 to the i power is equal to testNum (systemPage)
			if (Math.pow(2, i) == testNum)
			{
				//set ret to true
				ret = true;
			}
		}
		return ret;
	}
}
