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
        personsSet=new HashSet<>();
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

        Citi_Car sm1 = new Citi_Car(6, "Suzuki", "Swift", Fuel.BENZIN, 1000, true, "White");
        Off_Road_Car st1 = new Off_Road_Car(28, "Toyota", "RAV4", Fuel.DIESEL, 2500, 16);
        Amfibia am1 = new Amfibia(15, "UNIMAG", "AM!", Fuel.ELECTICITI, 150, true, true);

        Thing t1 = new Thing("grabie", 1);
        Thing t2 = new Thing("lopata", 2);
        Thing t3 = new Thing("kosiarka", 2);
        Thing t4 = new Thing("betoniarka", 4);

        items.add(t1);
        items.add(t2);
        items.add(t3);
        items.add(t4);

        items.add(sm1);
        items.add(st1);
        items.add(am1);


        // tu automat ladowanie z pliku


        personsSet.add(p1);
        personsSet.add(p2);
        personsSet.add(p3);

        roomSet.add(ap);
        roomSet.add(ap1);
        roomSet.add(ap2);
        roomSet.add(pp1);
        roomSet.add(pp2);
        roomSet.add(ap3);



        estate.put(ap.getId(), null);
        estate.put(ap1.getId(), null);
        estate.put(ap2.getId(), null);
        estate.put(pp1.getId(), null);
        estate.put(pp2.getId(), null);
        estate.put(ap3.getId(), null);




    }

//===============================================================    

    public void run() {



        Person user=null;



        while (true) {


            Apartment apartmentTemp=null;
            Garage garageTemp=null;
            Item itemTemp=null;

            System.out.println();
            System.out.println(" Wybierz odpowiednia cyfre " + '\n');
            System.out.println("  1.  Pokaz wszystkie osoby / Zaloguj sie ");
            System.out.println("  2.  Pokaz wszystkie wolne mieszkania i garaze");
            System.out.println("  3.  Pokaz wszystkie mieszkania");
            System.out.println("  4.  Pokaz wszystkie garaze");
            System.out.println("  5.  Pokaz wolne mieszkania/ Wynajem mieszkania !");
            System.out.println("  6.  Zwolnij mieszkanie");
            System.out.println("  7.  Pokaz lokatorow w mieszkaniu");
            System.out.println("  8.  Dodaj lokatora do mieszkania");
            System.out.println("  9.  Usun lokatora z mieszkanaia ");
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


            if(user==null){
                number="1";
            }else{
            number =choose(scan);}

            switch(number){

                case "1"  -> {
                    user = choosePerson(personsSet);
                    if (user==null) {
                        System.out.println(" INFO : osoba o padanym peselu nie istnieje,badz podales nieprawidlowy numer, sprobuj jeszcze raz !");
                    } else {
                        System.out.print(" W chwili obecnej uzytkownikiem jest osoba  ");
                        System.out.println(user);
                    }
                }

                case "2"  ->{
                    show(freeAllRooms(estate,roomSet));
                }

                case "3"  ->{

                    show(allRooms(roomSet,Apartment.class));
                }

                case "4"  ->{

                    show(allRooms(roomSet,Garage.class));
                }

                case "5"  ->{System.out.println("5");
                show(freeApartments(estate));}
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
                case "q" ->{System.exit(126);}
                case "Q" ->{System.exit(127);}

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
        return scan.nextLine();
    }

    // 2. pokaz wolne mieszkania
    public List<Apartment>freeApartments(Map<UUID,String>estate){
        List<Apartment>apartments=new ArrayList<>();
        for(Map.Entry<UUID,String>entry : estate.entrySet()) {
            if(entry.getValue()==null){
                for(Room r:roomSet){
                    if((r instanceof Apartment)&&(entry.getKey().equals(r.getId()))){
                        apartments.add((Apartment) r);
                    }
                }
            }
        }
        return apartments;
    }

    // 3. pokaz wolne garaze
    public List<Garage>freeGarage(Map<UUID,String>estate, List<Garage>garages){
        for(Map.Entry<UUID,String>entry : estate.entrySet()) {
            if(entry.getValue()==null){
                for(Room r:roomSet){
                    if((r instanceof Garage)&&(entry.getKey().equals(r.getId()))){
                        garages.add((Garage) r);
                    }
                }
            }
        }
        return garages;
    }

    // 4. pokaz wszystkie WOLNE mieszkania i garaze
    public List<Room>freeAllRooms(Map<UUID,String>estate,Set<Room>roomSet){
        List<Room>allRooms=new ArrayList<>();
        for(Map.Entry<UUID,String>entry : estate.entrySet()) {
            if(entry.getValue()==null){
                for(Room r:roomSet){
                    if(r.getId().equals(entry.getKey())){
                    allRooms.add(r);}
                }
            }
        }
        return allRooms;
    }

    // 4. pokaz wszystkie mieszkania i garaze
    public List<Room> allRooms(Set<Room>rooms,List<Room>roomList){
        roomList.addAll(rooms);
        return roomList;
    }

    // pokaz wszystkie mieszkania
   /* public List<Apartment> allApartments(Set<Room>rooms){
        List<Apartment>apartments=new ArrayList<>();
        for(Room r:rooms){
            if(r.getClass().equals(Apartment.class)){
                apartments.add((Apartment) r);
            }
        }
        return apartments;
    }*/

    // pokaz wszystkie garaze lub mieszkania
    public <T extends Room>List<T> allRooms(Set<Room>rooms,Class<T>propertyClass){
        List<T>room=new ArrayList<>();
        for(Room r:rooms){
            //if(r instanceof propertyClass){
            if(propertyClass.isInstance(r)) {
                room.add((T) r);
            }
        }
        return room;
    }

    // 5. pokaz wszystkich person

    public List<Person> allPersons(Set<Person>persons){
        //List<Person>personsList=new ArrayList<>(persons);
        //personsList.addAll(persons);
        //return personsList;
        return new ArrayList<>(persons);
    }

    // 6. pokaz przedmioty w garazu

    public List<Item> allItems(Garage garage){
        //items.addAll(garage.getItemsInGarage());
    return new ArrayList<>(garage.getItemsInGarage());
    }

    // 7. pokaz zameldowanych w mieszkaniu

    public List<Person> personsInApartment(Apartment apartment,List<Person>personsInApart){
        //personsInApart.addAll(apartment.getPersonsInApartment());
        //return personsInApart;
        return new ArrayList<>(apartment.getPersonsInApartment());
    }

    // 8. wybierz wolny apartament  !!!!   UWAGA NIEDOKONCZONE !!!!
    public Apartment chooseFreeApartment() {

        System.out.println(" Prosze podac numer pod ktorym znajduje sie apartament ktory chcesz wybrac  : " + '\n');
        show(freeApartments(estate));
        System.out.println();
        String id = choose(scan);
        return null;
    }
    //8A. wybierz apartament usera w ktorym chcesz cos zrobic

    // 9. wybierz garaz

    //9A. wybierz garaz usera w ktorym chcesz cos zrobic

    // 10. wybierz usera
    public Person getPerson(String pesel,Set<Person>persons){

        Person user=null;
        for(Person p:persons){
            if(pesel.equals(p.getPesel())){
                user=p;
            }
        }
    return user;}

//11. pokaz wydrukuj ponumerowana liste
    public <T> void show(List<T>list){
        int i=1;
        for(T item:list){
            System.out.println( (i++)+". "+item);
        }
    }

    // 12. wybor osoby

    public Person choosePerson(Set<Person> personsSet) {

        System.out.println(" Prosze podac pesel osoby ktora chcesz wybrac  : " + '\n');
        show(allPersons(personsSet));
        System.out.println();
        String pesel = choose(scan);
        return getPerson(pesel, personsSet);
    }






}
