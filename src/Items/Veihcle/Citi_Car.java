package Items.Veihcle;

public class Citi_Car extends Veihcle{

    private final boolean park_asistant;
    private String color;


    public Citi_Car(long size, String marke, String model, Fuel fuel, int engine_Capacity, boolean park_asistant, String color) {
        super(size, marke, model, fuel, engine_Capacity);
        this.park_asistant = park_asistant;
        this.color = color;
    }

    public boolean isPark_asistant() {
        return park_asistant;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

        return "Citi_Car{" +super.toString()+
                "park_asistant=" + park_asistant +
                ", color='" + color + '\'' +
                '}';
    }
}
