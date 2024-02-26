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


import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Environment {

    // try 2 ways !!!

    TimeInApp timeInApp;
    Set<Person> personsSet;
    Set<Room> roomSet;

    Set<Item> items;

    //List<Person> personsList;
    //List<Room>roomsList;

    Map<UUID, String> estate;
    //Map<Room,Person> estateFull;

    Scanner scan = new Scanner(System.in);


    public Environment() {

        estate = new HashMap<>();
        personsSet = new HashSet<>();
        roomSet = new HashSet<>();
        items = new HashSet<>();


        timeInApp=new TimeInApp(this);

//===============================================================


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


        timeInApp.timeRun(this);

        Person user = null;



        while (true) {


            Apartment apartmentTemp = null;
            Garage garageTemp = null;
            Item itemTemp = null;
            Person tempPerson=null;

            String number;

            if (user == null) {
                number = "1";
            } else {

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

                number = choose(scan);

            }


            switch (number) {

                case "1" -> {
                    user = choosePerson(personsSet);
                    if (user == null) {
                        System.out.println(" INFO : osoba o padanym peselu nie istnieje,badz podales nieprawidlowy numer, sprobuj jeszcze raz !");
                    } else {
                        System.out.print(" W chwili obecnej uzytkownikiem jest osoba  ");
                        System.out.println(user);
                    }
                }

                case "2" -> {

                    show(freeRoomsApartGar(Room.class));
                }

                case "3" -> {

                    show(allRooms(roomSet, Apartment.class));

                }

                case "4" -> {

                    show(allRooms(roomSet, Garage.class));

                }

                case "5" -> {

                    //if(fiveRoomRentLimitation(user)){
                    rentRoom(chooseRoom(Apartment.class),user);
                //}
                    //else {
                      //  System.out.println(" user ma wynajetych 5 nieruchomosci ");
                    //}

                }


                case "6" -> {

                    unrentRoom(chooseUserRoom(Apartment.class,user),user);
                }
                case "7" -> {
                    System.out.println("7");
                    show(personsInApartment(chooseUserRoom(Apartment.class,user)));

                }
                case "8" -> {

                    System.out.println( "  UWAGA PODAC PESEL !!!");
                    // zrobic cos z peselem
                    tempPerson=choosePerson(personsSet);
                    System.out.println( "  OSOBA DO DODANIA "+ tempPerson);
                    Apartment apartment=chooseUserRoom(Apartment.class,user);
                    if(apartment==null){
                        System.out.println(" nie mozna dokonac tej operacji !");
                    }else if(apartment.getPrimaryTenant().equals(tempPerson)){
                        System.out.println(" W tym momencie nie mozna usunac glownego lokatora-najemce !");
                    }else{
                    apartment.addPersonToApartment(tempPerson);
                }
                }
                case "9" -> {
                    // PRzetestowac
                    System.out.println("9");
                    Apartment apartment = chooseUserRoom(Apartment.class, user);
                    if (apartment == null) {
                        System.out.println(" nie mozna dokonac tej operacji !");
                        break;
                    } else{
                        tempPerson = choosePerson(apartment.getPersonsInApartment());}

                        if ((tempPerson == null)||(apartment.getPrimaryTenant().equals(tempPerson))) {
                            System.out.println(" nie ma zameldowanych innych osob poza glownym wynajmujacym badz podana osoba jest glownym najemca ! ");
                        } else {

                            apartment.removePersonFromApartment(tempPerson);
                    }
                }
                case "10" -> {

                    if(fiveRoomRentLimitation(user)){
                    rentRoom(chooseRoom(Garage.class),user);
                }else{
                        System.out.println(" user ma wynajetych 5 nieruchomosci ");
                    }
                }
                case "11" -> {

                    unrentRoom(chooseUserRoom(Garage.class,user),user);
                }
                case "12" -> {

                    show(allItemsInGarage(chooseUserRoom(Garage.class,user)));

                }
                case "13" -> {

                    itemTemp=chooseItem(items);
                    System.out.println( "  item DO DODANIA "+ itemTemp);
                    Garage garage=chooseUserRoom(Garage.class,user);
                    garage.addItemsToGarage(itemTemp);
                }
                case "14" -> {

                    Garage garage=chooseUserRoom(Garage.class,user);
                    itemTemp=chooseItem(garage.getItemsInGarage());
                    garage.removeItemFromGarage(itemTemp,items);
                }
                case "15" -> {

                    show(roomsOfUser(Room.class,user));
                }
                case "16" -> {

                    show(allItemsOfUser(user));
                }
                case "17" -> {
                    System.out.println(" DANE UZYTKOWNIKA");
                    System.out.println(user);
                    System.out.println(" NIERUCHOMOSCI WYNAJMOWANE PRZEZ UZYTKOWNIKA");
                    show(roomsOfUser(Room.class,user));
                    System.out.println(" WSZYSTKIE PRZEDMIOTY UZYTKOWNIKA");
                    show(allItemsOfUser(user));
                }

                case "18" -> {
                    System.out.println(" KONIEC PROGRAMU ");
                    //timeInApp.timeRun(this) scheduledExecutorService.shutdown();
                    System.exit(125);
                }
                case "q" -> {
                    System.exit(126);
                }
                case "Q" -> {
                    System.exit(127);
                }

                default -> {
                    System.out.println(" wrong number ");
                    System.out.println(" try again ");
                }
            }

        }
    }


    public String choose(Scanner scan) {

        System.out.println(" wybierz odpowiedni numer !");
        return scan.nextLine();
    }

    public <T extends Room>List<T> freeRoomsApartGar(Class<T>propertyClass) {
        List<T> rooms = new ArrayList<>();
        for (Map.Entry<UUID, String> entry : estate.entrySet()) {
            if(entry.getValue()==null){
                for (Room r : roomSet) {
                    if ((propertyClass.isInstance(r)) && (entry.getKey().equals(r.getId()))) {
                        rooms.add((T)r);
                    }
                }
            }
        }
        return rooms;
    }

    public <T extends Room> List<T> allRooms(Set<Room> rooms, Class<T> propertyClass) {
        List<T> room = new ArrayList<>();
        for (Room r : rooms) {
            if (propertyClass.isInstance(r)) {
                room.add((T) r);
            }
        }
        return room;
    }

    public List<Person> allPersons(Set<Person> persons) {
        return new ArrayList<>(persons);
    }
    public List<Item> allItems(Set<Item> items) {
        return new ArrayList<>(items);
    }


    public List<Item> allItemsInGarage(Garage garage) {
        if(garage==null){
            return new ArrayList<>();
        }else{
        return new ArrayList<>(garage.getItemsInGarage());
    }
    }


    public List<Person> personsInApartment(Apartment apartment) {
        if(apartment==null){
            return new ArrayList<>();
        }
        return new ArrayList<>(apartment.getPersonsInApartment());
    }


    public <T extends Room> List<T> roomsOfUser(Class<T>propertyClass,Person user){
        List<UUID>ids=new ArrayList<>();
        List<T>rooms=new ArrayList<>();
        for(Map.Entry<UUID,String>entry:estate.entrySet()){
             if(entry.getValue()==user.getPesel()){
                 ids.add( entry.getKey() );
                     }
        }
             for(Room r:roomSet){
                 for(UUID id:ids){
                     if((r.getId().equals(id))&&(propertyClass.isInstance(r))){
                         rooms.add((T)r);
                     }
                 }
             }

             return rooms;}



    public Person getPerson(String pesel) {

        Person user = null;
        for (Person p : personsSet) {
            if (pesel.equals(p.getPesel())) {
                user = p;
            }
        }
        return user;
    }
    public Item getItem(UUID itemId) {

        Item item = null;
        for (Item i : items) {
            if (itemId.equals(i.getId())) {
                item = i;
            }
        }
        return item;
    }


    public <T> void show(List<T> list) {
        if (!list.isEmpty()) {
            int i = 1;
            for (T item : list) {
                System.out.println((i++) + ". " + item);
            }
        }
    }



    public Person choosePerson(Set<Person> personsSet) {

        System.out.println(" Prosze podac pesel osoby ktora chcesz wybrac  : " + '\n');
        show(allPersons(personsSet));
        System.out.println();
        String pesel = choose(scan);
        return getPerson(pesel);
    }



    public <T extends Room> T  chooseRoom(Class<T>propertyClass) {

        if (freeRoomsApartGar(propertyClass).size() == 0) {
            System.out.println(" aktualnie brak wolnch pozycji do wynajecia");
            return null;
        }else{

            System.out.println(" Prosze podac numer pod ktorym znajduje sie apartment,ktory chcesz wybrac  : " + '\n');
            show(freeRoomsApartGar(propertyClass));

            System.out.println(" numer  :");
            int position = -1;
            try {
                position = (Integer.parseInt(choose(scan))) - 1;

            } catch (NumberFormatException e) {
                System.out.println(" wprowadzono nieprawidlowa liczbe , sprobuj ponownie !");
            }

            if ((position < 0) || (position > freeRoomsApartGar(propertyClass).size() - 1)) {
                return null;
            } else {
                System.out.println(freeRoomsApartGar(propertyClass).get(position));
                return freeRoomsApartGar(propertyClass).get(position);
            }

        }
    }

    public <T extends Room> void rentRoom( T room , Person user) {

        if(!fiveRoomRentLimitation(user)){
            System.out.println(" uzytkownik ma juz na koncie 5 wynajetych obiektow !");
        }else if(!threeFileLimitation(user)){
            System.out.println(" uzytkownik ma juz trzy ostrzezenia na koncie i nie moze wynajac w tym momencie zadnego nowego obiektu !");
        }else if (room != null) {
                for (Map.Entry<UUID, String> entry : estate.entrySet()) {
                    if (entry.getKey() == room.getId()) {

                        System.out.println(" podaj ilosc dni wynajmu  :");
                        long rentDays = -1;
                        try {
                            rentDays = Long.parseLong(choose(scan));
                        } catch (NumberFormatException e) {
                            System.out.println(" wprowadzono nieprawidlowa liczbe , powrot do glownego menu ... ");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException ie) {
                                ie.printStackTrace();
                            }
                        }

                        if (rentDays <= 0) {
                            System.out.println(" liczba dni musi byc wieksza niz zero ! Sprobuj ponownie.");
                        } else {
                            if (room instanceof Apartment) {
                                Apartment apartment = (Apartment) room;


                                apartment.setEndRent(new LocalDate[]{LocalDate.now().plusDays(rentDays)});
                                entry.setValue(user.getPesel());
                                apartment.setPrimaryTenant(user);


                                apartment.addPersonToApartment(user);

                                apartment.setStartRent(new LocalDate[]{LocalDate.now()});
                                System.out.println();
                                System.out.println(user + " wynaja  " + room);
                            } else if (room instanceof Garage) {
                                Garage garage = (Garage) room;

                                garage.setEndRent(new LocalDate[]{LocalDate.now().plusDays(rentDays)});
                                entry.setValue(user.getPesel());
                                garage.setPrimaryTenant(user);
                                garage.setStartRent(new LocalDate[]{LocalDate.now()});
                                System.out.println();
                                System.out.println(user + " wynaja  " + room);
                            }

                        }
                    }
                }


            } else {
                System.out.println(" prosze prawidlowo wybrac obiekt ! ");
            }

        }





    public <T extends Room> void unrentRoom( T room , Person user) {

        if (room != null) {
            for (Map.Entry<UUID, String> entry : estate.entrySet()) {
                if (entry.getKey() == room.getId()) {

                    entry.setValue(null);
                    room.setEndRent(new LocalDate[]{null});
                    room.setPrimaryTenant(null);
                    room.setStartRent(new LocalDate[]{null});

                    if (room instanceof Apartment) {
                        Apartment apartment = (Apartment) room;
                        apartment.removePersonFromApartment(user);
                        System.out.println();
                        System.out.println(user + " zwolnil  " + room);
                    }
                    else if (room instanceof Garage) {
                        Garage garage = (Garage) room;

                        //items.addAll(new HashSet<>(garage.getItemsInGarage()));

                        //clearGatage czysci garaz i zwraca do glownego setu itemy
                        garage.clearGarage(items);

                        System.out.println();
                        System.out.println(user + " zwolnil  " + room);
                    }
                    }

                }
        } else {
            System.out.println(" prosze prawidlowo wybrac obiekt ! ");
        }
    }

    public <T extends Room> T  chooseUserRoom(Class<T>propertyClass,Person user) {

        if (roomsOfUser(propertyClass, user).size() == 0) {
            System.out.println(" user nie ma jeszcze wynajetych zadnych nieruchomosci !");
            return null;
        } else {

            System.out.println(" Prosze podac numer pod ktorym znajduje sie nieruchomosc ,ktora chcesz wybrac  : " + '\n');

            show(roomsOfUser(propertyClass, user));

            {
                System.out.println(" numer  :");
                int position = -1;
                try {
                    position = (Integer.parseInt(choose(scan))) - 1;

                } catch (NumberFormatException e) {
                    System.out.println(" wprowadzono nieprawidlowa liczbe , sprobuj ponownie !");
                }

                if ((position < 0) || (position > roomsOfUser(propertyClass, user).size() - 1)) {
                    return null;
                } else {
                    return roomsOfUser(propertyClass, user).get(position);
                }
            }
        }
    }

    public Item chooseItem(Set<Item> items) {

        System.out.println(" Prosze podac numer pod ktorym znajduje sie obiekt ktory chcesz wybrac  : " + '\n');
        show(allItems(items));
        System.out.println();
        String position =choose(scan);
        int index=(Integer.parseInt(position))-1;
        UUID id = allItems(items).get(index).getId();


        return getItem(id);
    }
    public boolean fiveRoomRentLimitation(Person user){

        int count=0;

        for(Map.Entry<UUID,String>entry:estate.entrySet()){

            if(entry.getValue()==user.getPesel()){
                ++count;}
        }
        return count < 5;
    }

    public boolean threeFileLimitation(Person user){
        return user.getFiles().size()<3;
    }

    public List<Item> allItemsOfUser(Person user){

        List<Garage>tempGarage= roomsOfUser(Garage.class, user).stream().toList();

    return tempGarage.stream()
            .map(Garage::getItemsInGarage)
            .flatMap(Set::stream)
            .collect(Collectors.toList());
    }
}

