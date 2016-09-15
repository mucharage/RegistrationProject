
/**
 * extends and quadrilateral and gives area and perimeter for square
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 * 
 * @param sideLength  length of any side since they are all equal in a square
 * @param perimeter  the sum of all the sides
 * @param area  area of the polygon
 * 
 */
public class Square extends Quadrilateral
{

    double sideLength;
    double perimeter; 
    double area; 

    Square(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * @return area  the area of the polygon
     */
    @Override
    public double area() 
    {
        this.area = sideLength * sideLength;
        return area;
    }

    /**
     * @return perimeter  the perimeter of the polygon
     */
    @Override
    public double perimeter()
    {
        this.perimeter = sideLength * 4.0 ;
        return perimeter;
    }
}
