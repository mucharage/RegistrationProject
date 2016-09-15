
/**
 * Class to test the class Summer.
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 */
public class SummerTester
{
   public static void main(String[] args)
   {
       //create new Summer obeject with explicit @params of 1 and 100
       Summer firstRange = new Summer (1,100);
       
       //create new Summer obeject with explicit @params of 1 and 100
       Summer secondRange = new Summer (100,1000);
       
       //Print the sums
       System.out.println("The sum of integers 1-100 is: " + firstRange.getSum());
       System.out.println("The sum of integers 100-1000 is: " + secondRange.getSum());
   }
}
