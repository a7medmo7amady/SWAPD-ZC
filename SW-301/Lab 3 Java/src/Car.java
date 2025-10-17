public class Car extends Engine{
    protected  String Brand;
    protected  String Model;
    protected  Engine engine;
    public Car (String Brand, String Model, Engine engine) {
        this.Brand = Brand;
        this.Model = Model;
        this.engine = engine;
    }
    public void showCar() {
        System.out.println("Brand: " + Brand);
        System.out.println("Model: " + Model);
//        System.out.println("Engine: " + engine);
        System.out.println(engine.getHp());
        System.out.println(engine.getType());

    }

}
