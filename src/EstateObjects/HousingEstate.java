package EstateObjects;

import java.util.ArrayList;
import java.util.List;

public class HousingEstate {

    String name;
    List<ApartmentBuilding> listApartmebtBuldings;

    public HousingEstate(String name){

        this.name=name;
        listApartmebtBuldings=new ArrayList<>();
    }
}
