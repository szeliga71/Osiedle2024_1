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

    public void addPersonToApartment(Person person) {
        personsInApartment.add(person);
    }
    public void removePersonFromApartment(Person person){
        personsInApartment.remove(person);
    }

    @Override
    public String toString() {
        return "Apartment "+super.toString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Set<Person> getPersonsInApartment() {
        return personsInApartment;
    }
}
