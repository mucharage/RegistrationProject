
/**
 * Greeter class which swaps the
 * two names steven and ashley
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 */
public class Greeter
{
    /** instance varible to hold content sent from tester */
    private String name;
    
    /**
     *  Constructor that initializes instance variables
     */
    public Greeter(String aName)
    {
        // initialise instance variable 
        name = aName;
    }
    
    /**
     * Greets a person
     * 
     * @return     a greeting message to the content of name
     */
    public String greetPerson()
    {
        return "Nice to meet you, " + name + ".";
    }
    
    /**
     * Swaps the content of name in the initial Greeter object
     * with the content stored in the other Greeter object
     * 
     * @param other  the name of the other object, not null
     */
    public void swapNames(Greeter other)
    {
        /** holder acts as a value holder for name 
         * since name changes to other 
         */
        String holder = name;
        // name  is set to value of other
        this.name = other.name;
        // other is set to value of original name held by holder
        other.name = holder;
    }
}