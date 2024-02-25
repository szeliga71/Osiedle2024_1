package Items.Veihcle;

public class Amfibia extends Veihcle implements Terrain,Water{

    private boolean have_anchor;

    private boolean have_propeller;

    public Amfibia(long size, String marke, String model, Fuel fuel, int engine_Capacity, boolean have_anchor, boolean have_propeller) {
        super(size, marke, model, fuel, engine_Capacity);
        this.have_anchor = have_anchor;
        this.have_propeller = have_propeller;
    }

    public boolean isHave_anchor() {
        return have_anchor;
    }

    public void setHave_anchor(boolean have_anchor) {
        this.have_anchor = have_anchor;
    }

    public boolean isHave_propeller() {
        return have_propeller;
    }

    public void setHave_propeller(boolean have_propeller) {
        this.have_propeller = have_propeller;
    }

    @Override
    public void drive() {
        System.out.println(" Go through the bushes !");
    }

    @Override
    public void sail() {
        System.out.println(" sail boldly into the wind");

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
        return "Amfibia{}"+super.toString();
    }
}
