
/**
 * Class that calculates the sum of all
 * integers between a given range
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 */
public class Summer
{
    /**
     * @param sum  running total of integers
     */
    private int sum = 0;
    
    /**
     * @param startNum  the smallest integer in the range
     */
    private int startNum = 0;
    
    /**
     * @param endNum  the largest integer in the range
     */
    private int endNum = 0;
    
    /**
     * Constructor that initializes instance variables
     */
    public Summer(int smallestNum, int largestNum)
    {
        startNum = smallestNum;
        endNum = largestNum;
    }
    
    /**
     * method to calculate the sum of integers
     * from smallest number to largest
     * 
     * @param currentNum  the number being added to sum at the current time
     * 
     * @return sum  the sum of all integers startNum to endNum
     */
    public int getSum()
    {
        int currentNum = startNum;
        
        //while the current number is less than or equal to the end number
        while(currentNum <= endNum)
        {
            // update sum by adding the current integer to it
            sum += currentNum;
            // increment currentNum by 1
            currentNum++;
        }
        return sum;
    }
}
