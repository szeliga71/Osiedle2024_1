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
}
