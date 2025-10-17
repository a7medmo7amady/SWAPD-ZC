public class Engine {
    protected   double Hp;
    protected   String Type;
    Engine (double Hp, String Type){
        this.Hp = Hp;
        this.Type = Type;
    }
    public double getHp() {
        return Hp;
    }

    public String getType() {
        return Type;
    }

    public Engine() {
    }
}
