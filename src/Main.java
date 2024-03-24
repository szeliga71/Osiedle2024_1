import Environment.Environment;
import EstateObjects.Apartment;
import EstateObjects.Garage;
import Items.Thing.Thing;
import Items.Veihcle.Amfibia;
import Items.Veihcle.Citi_Car;
import Items.Veihcle.Fuel;
import Items.Veihcle.Off_Road_Car;
import People.Nation;
import People.Person;


public class Main {

    public static void main(String[] args) {


        Environment en = new Environment();

        // dane do pracy
        Person p1 = new Person("43", "Jan", "Kowalski", Nation.POLISH);
        Person p2 = new Person("45", "Bronislaw", "Cieslak", Nation.FRENCH);
        Person p3 = new Person("44", "Kamil", "Nowak", Nation.DEUTSCH);


        Apartment ap = new Apartment(120);
        Apartment ap1 = new Apartment(159);
        Apartment ap2 = new Apartment(220);
        Apartment ap3 = new Apartment(123);
        Garage pp1 = new Garage(30);
        Garage pp2 = new Garage(35);

        Citi_Car sm1 = new Citi_Car(20, "Suzuki", "Swift", Fuel.BENZIN, 1000, true, "White");
        Off_Road_Car st1 = new Off_Road_Car(28, "Toyota", "RAV4", Fuel.DIESEL, 2500, 16);
        Amfibia am1 = new Amfibia(15, "UNIMAG", "AM!", Fuel.ELECTRICITI, 150, true, true);

        Thing t1 = new Thing("grabie", 1);
        Thing t2 = new Thing("lopata", 2);
        Thing t3 = new Thing("kosiarka", 2);
        Thing t4 = new Thing("betoniarka", 4);

        en.getItems().add(t1);
        en.getItems().add(t2);
        en.getItems().add(t3);
        en.getItems().add(t4);

        en.getItems().add(sm1);
        en.getItems().add(st1);
        en.getItems().add(am1);


        // tu automat ladowanie z pliku


        en.getPersonsSet().add(p1);
        en.getPersonsSet().add(p2);
        en.getPersonsSet().add(p3);

        en.getRoomSet().add(ap);
        en.getRoomSet().add(ap1);
        en.getRoomSet().add(ap2);
        en.getRoomSet().add(pp1);
        en.getRoomSet().add(pp2);
        en.getRoomSet().add(ap3);


        en.getEstate().put(ap.getId(), null);
        en.getEstate().put(ap1.getId(), null);
        en.getEstate().put(ap2.getId(), null);
        en.getEstate().put(pp1.getId(), null);
        en.getEstate().put(pp2.getId(), null);
        en.getEstate().put(ap3.getId(), null);

        en.run();


    }
}




