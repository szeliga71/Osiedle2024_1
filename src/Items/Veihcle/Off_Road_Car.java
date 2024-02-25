package Items.Veihcle;

public class Off_Road_Car extends Veihcle implements Terrain{

    private int  how_many_halogens;

    public Off_Road_Car(long size, String marke, String model, Fuel fuel, int engine_Capacity, int how_many_halogens) {
        super(size, marke, model, fuel, engine_Capacity);
        this.how_many_halogens = how_many_halogens;
    }

    public int getHow_many_halogens() {
        return how_many_halogens;
    }

    public void setHow_many_halogens(int how_many_halogens) {
        this.how_many_halogens = how_many_halogens;
    }

    @Override
    public void drive() {
        System.out.println(" drive forward !");
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Off_Road_Car{" +super.toString()+
                "how_many_halogens=" + how_many_halogens +
                '}';
    }
}
