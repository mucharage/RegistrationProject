
/**
 * Write a description of class DataAnalyzer here.
 * @author (Hullander, Steven)  
 * @version (Hw1)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class DataAnalyzerTester
{
    /**
     * @exception FileNotFoundException if inputFileName does not exist.
     */
    public static void main(String[] args)
    {
        // scanner object to read user input
        Scanner console = new Scanner(System.in);
        
        // scanner object to read from file
        Scanner in = null;
        
        // directory of file read, can be UI also
        String directory = "C:\\Users\\steven\\Desktop\\";
        System.out.print("Input file: ");
        
        // add the name of the file to the directory
        String inputFileName = directory + console.next();
        try 
        {
            in = new Scanner(new File(inputFileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.print("File does not exist.");
        }
        
        // arrayList to hold integers in the array
        ArrayList<Integer> numList = new ArrayList<Integer>();
        
        // while the file has more content
        while(in.hasNext())
        {
            // if the content is an integer
            if(in.hasNextInt())
            {
                // save the integer to value
                int value = in.nextInt();
                // add the integer stored as value to arrayList
                numList.add(value);
            }
            // if the content is not a integer, skip it
            else 
            {
                in.next();
            }
        }
        // CLOSE THE FILE
        in.close();
      
        // create new DataAnalyzer object 
        DataAnalyzer sendList = new DataAnalyzer(numList);
        
        // line for spacing and neatness
        System.out.println("");
        
        // print the arrayList
        System.out.println("The integers from the file " + inputFileName + " are:");
        System.out.println(numList);
        
        // line for spacing and neatness
        System.out.println("");
        
        // send DataAnalyzer object that holds the arrayList to get the max,min, and average
        sendList.getMax();
        sendList.getMin();
        sendList.getAverage();
    }
}
