package EstateObjects;

import Items.Item;

import java.util.HashSet;
import java.util.Set;

public class Garage extends Room{

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

    public void addItemsToGarage(Item item){
        if(item.getSize()+volume<this.getArea()){
        itemsInGarage.add(item);
        setVolume(item.getSize()+volume);}
        else{
            System.out.println(" nie ma wystarczajaco miejsca w garazu ");
        }

    }
    public void removeItemFromGarage(Item item,Set<Item>items){

        itemsInGarage.remove(item);
        items.add(item);
    }

    public void clearGarage(Set<Item> items){
        items.addAll(this.getItemsInGarage());
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

}
