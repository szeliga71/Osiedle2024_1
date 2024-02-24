package Items.Veihcle;

import Items.Item;

public class Motoboat extends Item implements Water{


    private final int longest;
    private final Fuel fuel;
    public Motoboat(long size,int longest,Fuel fuel) {
        super(size);
        this.longest=longest;
        this.fuel=fuel;
    }

    public int getLongest() {
        return longest;
    }

    public Fuel getFuel() {
        return fuel;
    }

    @Override
    public void sail() {
        System.out.println( " just sail ...");
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
