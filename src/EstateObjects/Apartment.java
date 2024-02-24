package EstateObjects;

import People.Person;


import java.util.HashSet;
import java.util.Set;

public class Apartment extends Room{

     private Set<Person> personsInApartment;

    public Apartment(long area){
        super.setArea(area);
        personsInApartment=new HashSet<>();
    }

    public Set<Person> getPersonsInApartment() {
        return personsInApartment;
    }

    @Override
    public String toString() {
        return "Apartment "+super.toString();
    }
}
