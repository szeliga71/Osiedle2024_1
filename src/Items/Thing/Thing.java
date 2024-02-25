package Items.Thing;

import Items.Item;

public class Thing extends Item {

    private final String name;

    public Thing(String name,long size){
        super(size);
        this.name=name;

    }

    public String getName() {
        return name;
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
        return
                name +" "+ super.toString();

    }
}
