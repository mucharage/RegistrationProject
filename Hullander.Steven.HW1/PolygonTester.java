
/**
 * class to test multiple classes
 * 
 * credit to BNats for using bits of his/her code
 * @link http://stackoverflow.com/questions/25477015/interface-and-inheritance-compile-time-error
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 */
import java.util.Scanner;
public class PolygonTester
{
    public static void main(String[] args)
    {
        int numberSides;
        double length;
        double base;
        
        // new Scanner object to see how many sides user wants
        Scanner sides = new Scanner(System.in);

        /**
         * Ask if the user wants a 3 or 4 sided polygon
         * until the user enter '3' or '4'
         */
        do 
        {
            System.out.println("Would you like a 3 or 4 sided polygon?");
            numberSides = sides.nextInt();
        } while (numberSides != 3 && numberSides != 4);

        /**
         * If the user wants a 3 sided polygon ask what the lenths of the sides are
         * then create an IsoscelesTriangle object
         * 
         * @param length  the length of the sides that are equal
         * @param base  the length of the base
         */
        if (numberSides == 3)
        {
            System.out.println("How long are the sides that are the same lenth?");
            length = sides.nextDouble();
            System.out.println("What is the length of the base?: ");
            base = sides.nextDouble();
            
            // create an IsoscelesTriangle object
            IsoscelesTriangle Isoc = new IsoscelesTriangle(length, base);
            
            //print the area with 2 decimals
            System.out.println("The area of the isoscelese triangle is: " + String.format( "%.2f", Isoc.area()));
            //print the perimeter with 2 decimals
            System.out.println("The perimeter of the isoscelese triangle is: " + String.format( "%.2f", Isoc.perimeter()));
        } 
        
        /**
         * if the user wants a 4 sided polygon determine if it is 
         * a square or rectangle by comparing 2 sets of sides
         */
        else 
        {
            System.out.println("What is the lenth of one side?");
            length = sides.nextDouble();
            System.out.println("What is the lenth of the other side?");
            base = sides.nextDouble();

            /**
             * if the lenths are the same, the user wants a square
             */
            if (length == base)
            {
                /**
                 * since the sides are all the same
                 * @param squareSide  the length of any side of the Square
                 */
                final double squareSide = length;
                
                // create an Square object
                Square Quad = new Square(squareSide);
                
                //print the area with 2 decimals
                System.out.println("The area of the square is: " + String.format( "%.2f", Quad.area()));
                
                //print the perimeter with 2 decimals
                System.out.println("The perimeter of the square is: " + String.format( "%.2f", Quad.perimeter()));
            } 
            
            /**
             * if the lenths are not the same, the user wants a rectangle
             */
            else
            {
                // create an Rectangle object
                Rectangle Quad = new Rectangle(length, base);
                
                //print the area with 2 decimals
                System.out.println("The area of the rectangle is: " + String.format( "%.2f", Quad.area()));
                
                //print the perimeter with 2 decimals
                System.out.println("The perimeter of the rectangle is: " + String.format( "%.2f", Quad.perimeter()));
            }
        }
    }

}
