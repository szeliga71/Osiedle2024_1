package Environment;

import EstateObjects.Apartment;
import People.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class LocatorAddRemove extends ExtendRent{

    public void removeLocatorFromApartment(Apartment apartment){

        Optional<Person> tempPersonO = chooseOptionalPerson(apartment.getPersonsInApartment());
        if ((tempPersonO.isPresent()) &&!(apartment.getPrimaryTenantID().equals(tempPersonO.get().getPesel()))) {
            apartment.removePersonFromApartment(tempPersonO.get());
            System.out.println(tempPersonO.get() + "  Wymeldowany ");}
        else{
            System.out.println(" nie ma zameldowanych innych osob poza glownym wynajmujacym badz podana osoba jest glownym najemca ! +'\n" +
                    "W tym momencie nie mozna tej osoby wymeldowac !");
        }
    }

    public void addLocatorToApartment( Set<Person> personsSet,Apartment apartment) {
        Optional<Person> tempPersonO = chooseOptionalPerson(personsSet);
        if (tempPersonO.isPresent()) {
            System.out.println("  OSOBA DO DODANIA " + tempPersonO.get());
            apartment.addPersonToApartment(tempPersonO.get());
        }
    }

    public List<Person> personsInApartment(Apartment apartment) {
        if (apartment == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(apartment.getPersonsInApartment());
    }
}
