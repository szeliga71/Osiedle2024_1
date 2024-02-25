package EstateObjects;

import Items.Item;

import java.util.HashSet;
import java.util.Set;

public class Garage extends Room{

   private Set<Item> itemsInGarage;

    public Garage(long area){
        super.setArea(area);
        itemsInGarage=new HashSet<>();
    }

    public Set<Item> getItemsInGarage() {
        return itemsInGarage;
    }

    public void addItemsToGarage(Item item){
        itemsInGarage.add(item);
    }
    public void removeItemFromGarage(Item item){
        itemsInGarage.remove(item);
    }
    public void clearGarage(){
        itemsInGarage.clear();
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
}