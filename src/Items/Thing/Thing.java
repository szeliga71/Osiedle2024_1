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
}
