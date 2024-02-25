package Items.Veihcle;

import Items.Item;

public abstract class Veihcle extends Item {

    private final String marke;
    private final String model;
    private final Fuel fuel;
    private final int engine_Capacity;

    public Veihcle(long size,String marke,String model,Fuel fuel,int engine_Capacity) {
        super(size);
        this.marke=marke;
        this.model=model;
        this.fuel=fuel;
        this.engine_Capacity=engine_Capacity;

    }

    public String getMarke() {
        return marke;
    }

    public String getModel() {
        return model;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public int getEngine_Capacity() {
        return engine_Capacity;
    }

    @Override
    public String toString() {
        return "Veihcle{" +
                "marke='" + marke + '\'' +
                ", model='" + model + '\'' +
                ", fuel=" + fuel +
                ", engine_Capacity=" + engine_Capacity +
                '}';
    }
}
