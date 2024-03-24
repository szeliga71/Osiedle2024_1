package EstateObjects;

import Items.Item;


import java.util.*;

public class Garage extends Room{

    protected Item item;

   private Set<Item> itemsInGarage;
   private long volume;

    public Garage(long area){
        super.setArea(area);
        itemsInGarage=new HashSet<>();
        volume=0;
    }



    public Set<Item> getItemsInGarage() {
        return itemsInGarage;
    }

    public void clearGarage(Set<Item>itemSet){
        addItemFromGarageToGlobal(itemSet);
        itemsInGarage.clear();
    }
    public void addItemFromGarageToGlobal(Set<Item>itemSet){
        itemSet.addAll(this.getItemsInGarage());

    }

    @Override
    public String toString() {
        return "Garage " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

}
