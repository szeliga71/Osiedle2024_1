package Items.Veihcle;

public class Motorrad extends Veihcle{

    private boolean enduro;
    public Motorrad(long size, String marke, String model, Fuel fuel, int engine_Capacity,boolean enduro) {
        super(size, marke, model, fuel, engine_Capacity);
        this.enduro=enduro;
    }

    public boolean isEnduro() {
        return enduro;
    }

    public void setEnduro(boolean enduro) {
        this.enduro = enduro;
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
