public class Circle extends Shape{
    protected   double radius;
    Circle(double radius){
        this.radius = radius;
    }
    @Override
    public double Area(){
        System.out.println(3.14*radius*radius);
        return 3.14*radius*radius;
    }
    @Override
    public double Perimeter(){
        System.out.println(3.14*radius*2);
        return 3.14*radius*2;
    }
}
