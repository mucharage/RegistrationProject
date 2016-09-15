
/**
 * Test the Greeter class which swaps the two
 * names steven and ashley
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 */
public class GreeterTester
{
    public static void main (String[] args)
    {
        // create two Greeter objects that hold names
        Greeter steven = new Greeter("Steven");
        Greeter ashley = new Greeter("Ashley");
        
        // test steven.greetPerson() before swap -- Expected: "Nice to meet you, Steven."
        System.out.println("First call to name1: " + steven.greetPerson());
        
        // test ashley.greetPerson() before swap -- Expected: "Nice to meet you, Ashley."
        System.out.println("First call to name2: " + ashley.greetPerson());
        
        // call swapNames() method to switch steven and ashley names
        steven.swapNames(ashley);
        
        // blank line for spacing and neatness
        System.out.println("");
        
        // test steven.greetPerson() after swap -- Expected: "Nice to meet you, Ashley."
        System.out.println("Second call to name1: " + steven.greetPerson());
        
        // test ashley.greetPerson() after swap -- Expected: "Nice to meet you, Steven."
        System.out.println("Second call to name2: " + ashley.greetPerson());
    }
}
