import java.awt.geom.Area;

public abstract class Shape {
    protected double area;
    protected double Perimeter;
    Shape(){
        System.out.println("The Shape ;");
    }
    public double Area(){
        System.out.println("The Shape Areas : ");
        return area;
    }

    public double Perimeter(){
        System.out.println("The Shape Perimeter : ");
        return Perimeter;
    }

//    public abstract double area();
}
