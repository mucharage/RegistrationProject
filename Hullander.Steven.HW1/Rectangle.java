
/**
 * extends and quadrilateral and gives area and perimeter for rectangle
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 * 
 * @param sideOne  length of one set of parallel sides (one side)
 * @param sideTwo  length of other set of parallel sides (one side)
 * @param perimeter  sum of the sides added together
 * @param area  area of the polygon
 */
public class Rectangle extends Quadrilateral
{

    double sideOne = this.sideOne; 
    double sideTwo = this.sideTwo; 
    double perimeter = this.perimeter; 
    double area = this.area; 

    Rectangle(double recievedSideOne, double recievedSideTwo) {
        sideOne = recievedSideOne;
        sideTwo = recievedSideTwo;
    }

    /**
     * @return area  the area of the ploygon
     */
    @Override
    public double area() 
    {
        area = sideOne * sideTwo;
        return area;
    }

    /**
     * @return perimeter  the perimeter of the ploygon
     */
    @Override
    public double perimeter()
    {
        this.perimeter = (sideOne + sideOne + sideTwo + sideTwo) ;
        return perimeter;
    }
}
