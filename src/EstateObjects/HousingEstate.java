package EstateObjects;

import java.util.ArrayList;
import java.util.List;

public class HousingEstate {

    String name;
    List<ApartmentBuilding> listApartmebtBuldings;
    List<Garage>garagesInEstate;

    public HousingEstate(String name){

        this.name=name;
        listApartmebtBuldings=new ArrayList<>();
        garagesInEstate=new ArrayList<>();
    }
}
