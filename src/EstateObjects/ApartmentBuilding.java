package EstateObjects;

import java.util.ArrayList;
import java.util.List;

public class ApartmentBuilding {

    int number;
    List<Apartment> apartmentsInBuilding;

    public ApartmentBuilding(int number){
        this.number=number;
        apartmentsInBuilding=new ArrayList<>();
    }
}
