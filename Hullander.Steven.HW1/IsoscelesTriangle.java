
/**
 * Write a description of class IsoscelesTriangle here.
 * 
 * @author (Hullander, Steven)  
 * @version (Hw1)
 * 
 * @param sideLength  length of the two equal sides
 * @param base  length of the base of the triangle
 * @param perimeter  the sum of all the sides
 * @param area  area of the polygon
 * 
 */
import java.lang.Math;
public class IsoscelesTriangle extends Triangle
{
    double sideLength=this.sideLength;
    double base=this.base;
    double perimeter=this.perimeter; 
    double area=this.area; 

    IsoscelesTriangle(double sideLength, double base) {
        this.sideLength = sideLength;
        this.base = base;
    }

    /**
     * @return area  the area of the ploygon
     */
    @Override
    public double area() 
    {
        this.area = (base/4) * Math.sqrt((4*Math.pow((sideLength),2)-Math.pow(base,2)));
        return area;
    }

    /**
     * @return perimeter  the perimeter of the ploygon
     */
    @Override
    public double perimeter()
    {
        this.perimeter = base + (2*sideLength) ;
        return perimeter;
    }
}