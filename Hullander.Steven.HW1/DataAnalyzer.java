
/**
 * Reads integers from a specified text document
 * and displays the maximum, minimum, and average values
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 */
import java.util.Collections;
import java.util.ArrayList;
public class DataAnalyzer
{
    //arrayList to recieve the array from the tester class
    private ArrayList<Integer> holdList;

    /**
     *  Constructor that initializes instance variables
     *  
     *  @param holdList  copy of arrayList recieved from tester (numbers)
     *  @param numbers  arrayList of integers sent from tester
     */
    public DataAnalyzer(ArrayList<Integer> numbers)
    {
        // creates copy of (numbers) arrayList
        holdList = new ArrayList<Integer>(numbers);

        // puts the values in the arrayList in order (smallest to largest)
        Collections.sort(holdList);
    }

    /**
     * method to determine the max value in the arrayList
     */
    public void getMax()
    {
        // get the last (largest) element in the arrayList and print it
        System.out.println("The max is: " + holdList.get(holdList.size()-1));
    }

    /**
     * method to determine the min value in the arrayList
     */
    public void getMin()
    {
        // get the first (smallest) element in the arrayList and print it
        System.out.println("The min is: " + holdList.get(0));
    }

    /**
     * method to determine the average value in the arrayList
     * 
     * @param average the total sum divided by the number of integers
     * @param sum  total of all the integers added together
     */
    public void getAverage()
    {
        int average=0;
        int sum=0;
        /** 
         * increment i by 1 while i is less than or equal to 
         *  one less than the number of integers is the arrayList
         */
        for(int i=0; i<=holdList.size()-1; i++)
        {
            // sum equals the integer at the element 'i' in the arrayList
            sum += holdList.get(i);
        }

        //total sum divided by number of integers
        average = sum/holdList.size();

        //print the average
        System.out.print("The average is: " + average);
    }
}
