package EstateObjects;

import Environment.TooManyThingsException;
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


    public void addItemsToGarage(Item item) throws TooManyThingsException{

        if(item.getSize()+volume<this.getArea()){
    throw new TooManyThingsException(" Niewystarczajaca ilosc miejsca w garazu "); }else{

            System.out.println(" mozna wlozyc obiekt do garazu ");
        }
        itemsInGarage.add(item);
        setVolume(item.getSize()+volume);}



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
