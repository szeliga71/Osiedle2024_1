package Environment;


import EstateObjects.Apartment;
import EstateObjects.Garage;
import EstateObjects.Room;
import Items.Item;
import Items.Thing.Thing;
import Items.Veihcle.Amfibia;
import Items.Veihcle.Citi_Car;
import Items.Veihcle.Fuel;
import Items.Veihcle.Off_Road_Car;
import People.Nation;
import People.Person;


import java.util.*;

public class Environment {

    // try 2 ways !!!

    Set<Person>personsSet;
    Set<Room> roomSet;
    
    Set<Item>items;

    //List<Person> personsList;
    //List<Room>roomsList;

    Map<UUID,String> estate;
    //Map<Room,Person> estateFull;

    Scanner scan=new Scanner(System.in);
    Person User;
    Apartment apartment;
    Garage garage;

    public Environment() {

        estate = new HashMap<>();
        roomSet = new HashSet<>();
        items = new HashSet<>();


//===============================================================


        // dane do pracy
        Person p1 = new Person("43","Jan", "Kowalski", Nation.POLISH);
        Person p2 = new Person("45","Bronislaw", "Cieslak",Nation.FRENCH);
        Person p3 = new Person("44","Kamil", "Nowak", Nation.DEUTSCH);


        Apartment ap = new Apartment(120);
        Apartment ap1 = new Apartment(159);
        Apartment ap2 = new Apartment(220);
        Apartment ap3 = new Apartment(123);
        Garage pp1 = new Garage(30);
        Garage pp2 = new Garage(35);
        //Citi_Car sm1 = new Citi_Car(6, "Suzuki", "Swift", Fuel.BENZIN, 1000, true, "White");
        //Off_Road_Car st1 = new Off_Road_Car(28, "Toyota", "RAV4", Fuel.DIESEL, 2500, 16);
        //Amfibia am1 = new Amfibia(15, "UNIMAG", "AM!", Fuel.ELECTICITI, 150, true, true);
        Thing t1 = new Thing("grabie", 1);
        Thing t2 = new Thing("lopata", 2);
        Thing t3 = new Thing("kosiarka", 2);
        Thing t4 = new Thing("betoniarka", 4);

        items.add(t1);
        items.add(t2);
        items.add(t3);
        items.add(t4);

        //items.add(sm1);
        //items.add(st1);
        //items.add(am1);


        // tu automat ladowanie z pliku


        personsSet.add(p1);
        personsSet.add(p2);
        personsSet.add(p3);


        estate.put(ap.getId(), null);
        estate.put(ap1.getId(), null);
        estate.put(ap2.getId(), null);
        estate.put(pp1.getId(), null);
        estate.put(pp2.getId(), null);
        estate.put(ap3.getId(), null);

    }

//===============================================================    

    public void run() {

        while (true) {

            System.out.println();
            System.out.println(" wybierz odpowiednia cyfre " + '\n');
            System.out.println("  1. Pokaz wszystkie osoby / Zaloguj sie ");
            System.out.println("  2. Pokaz wszystkie wolne mieszkania i garaze");
            System.out.println("  3. Pokaz wszystkie mieszkania");
            System.out.println("  4. Pokaz wszystkie garaze");
            System.out.println("  5. Pokaz wolne mieszkania/ Wynajem mieszkania !");
            System.out.println("  6. Zwolnij mieszkanie");
            System.out.println("  7. Pokaz lokatorow w mieszkaniu");
            System.out.println("  8. Dodaj lokatora do mieszkania");
            System.out.println("  9. Usun lokatora z mieszkanaia ");
            System.out.println("  10. Pokaz wolne garaze/ Wynajem garazu !");
            System.out.println("  11. Zwolnij garaz");
            System.out.println("  12. Pokaz wszystkie przedmioty w garazu");
            System.out.println("  13. Dodaj przedmiot do garazu");
            System.out.println("  14. Usun przedmiot z garazu");
            System.out.println("  15. Pokaz wszyskie nieruchomosci wynajmowane przez osobe");
            System.out.println("  16. Pokaz wszystkie przedmioty danej osoby");
            System.out.println("  17. Pokaz dane uzytkownika i wszystkie nieruchomosci i wszystkie przedmioty");
            System.out.println("  18. EXIT");

            String number;

            number =choose(scan);

            switch(number){

                case "1"  ->{System.out.println("1");}
                case "2"  ->{System.out.println("2");}
                case "3"  ->{System.out.println("3");}
                case "4"  ->{System.out.println("4");}
                case "5"  ->{System.out.println("5");}
                case "6"  ->{System.out.println("6");}
                case "7"  ->{System.out.println("7");}
                case "8"  ->{System.out.println("8");}
                case "9"  ->{System.out.println("9");}
                case "10" ->{System.out.println("10");}
                case "11" ->{System.out.println("11");}
                case "12" ->{System.out.println("12");}
                case "13" ->{System.out.println("13");}
                case "14" ->{System.out.println("14");}
                case "15" ->{System.out.println("15");}
                case "16" ->{System.out.println("16");}
                case "17" ->{System.out.println("17");}

                case "18" ->{
                    System.out.println(" KONIEC PROGRAMU ");
                    System.exit(125);}

                default -> {
                    System.out.println(" wrong number ");
                    System.out.println(" try again ");
                }
            }

        }
    }

    // metods

    // 1.  wybierz numer do case

    public String choose(Scanner scan){

        System.out.println(" wybierz odpowiedni numer !");
        return scan.nextLine();}

    // 2. pokaz wolne mieszkania
    public List<Apartment>freeApartments(Map<UUID,String>estate, List<Apartment>apartments){
        for(Map.Entry<UUID,String>entry : estate.entrySet()) {
            if(entry.getValue()==null){
                for(Room r:roomSet){
                    if((r.getClass()==Apartment.class)&&(entry.getKey().equals(r.getId()))){
                        apartments.add((Apartment) r);
                    }
                }
            }
        }
        return apartments;}

    // 3. pokaz wolne garaze
    public List<Garage>freeGarage(Map<UUID,String>estate, List<Garage>garages){
        for(Map.Entry<UUID,String>entry : estate.entrySet()) {
            if(entry.getValue()==null){
                for(Room r:roomSet){
                    if((r.getClass()==Garage.class)&&(entry.getKey().equals(r.getId()))){
                        garages.add((Garage) r);
                    }
                }
            }
        }
        return garages;}

    // 4. pokaz wszystkie mieszkania i garaze
    public List<Room> allRooms(Set<Room>rooms,List<Room>roomList){
        roomList.addAll(rooms);
        return roomList;}

    // 5. pokaz wszystkich person

    public List<Person> allPersons(Set<Person>persons,List<Person>personsList){
        personsList.addAll(persons);
        return personsList;}

    // 6. pokaz przedmioty w garazu
    public List<Item> allItems(Garage garage,List<Item>items){
        items.addAll(garage.getItemsInGarage());
    return items;}
    // 7. pokaz zameldowanych w mieszkaniu
    public List<Person> personsInApartment(Apartment apartment,List<Person>personsInApart){
        personsInApart.addAll(apartment.getPersonsInApartment());
        return personsInApart;}
    // 8. wybierz apartament
    // 9. wybierz garaz
    // 10. wybierz usera

   /* public UUID userId(Set<Person>personsSet,List<Person>personsList){
        int i=0;
        for(Person p:allPersons(personsSet,personsList)){
            System.out.println((i++)+". "+p);
        }
    }*/

    public Person getPerson(String pesel,Set<Person>persons){

        Person user=null;
        for(Person p:persons){
            if(pesel.equals(p.getPesel())){
                user=p;
            }
        }
    return user;}



}
