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


import java.time.LocalDate;
import java.util.*;

public class Environment {


    TimeInApp timeInApp;
    Set<Person> personsSet;
    Set<Room> roomSet;

    Set<Item> items;

    Map<UUID, String> estate;


    Scanner scan;


    public Environment() {

        estate = new HashMap<>();
        personsSet = new HashSet<>();
        roomSet = new HashSet<>();
        items = new HashSet<>();

        scan = new Scanner(System.in);

        timeInApp = new TimeInApp(this);

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
        Amfibia am1 = new Amfibia(15, "UNIMAG", "AM!", Fuel.ELECTRICITI, 150, true, true);

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


            Item itemTemp ;


            String number;

            if (user == null) {
                number = "1";
            } else {

                System.out.println();
                //System.out.println(/*" Wybierz odpowiednia cyfre " + */'\n');
                System.out.println("  1.  Pokaz wszystkie osoby / Zaloguj sie ");
                System.out.println("  2.  Pokaz wszystkie wolne mieszkania i garaze");
                System.out.println("  3.  Pokaz wszystkie mieszkania");
                System.out.println("  4.  Pokaz wszystkie garaze");
                System.out.println("  5.  Pokaz wolne mieszkania/ Wynajem mieszkania !");
                System.out.println("  6.  Przedluzenie wynajmu mieszkania ");
                System.out.println("  7.  Zwolnij mieszkanie");
                System.out.println("  8.  Pokaz lokatorow w mieszkaniu");
                System.out.println("  9.  Dodaj lokatora do mieszkania");
                System.out.println("  10.  Usun lokatora z mieszkanaia ");
                System.out.println("  11. Pokaz wolne garaze/ Wynajem garazu !");
                System.out.println("  12.  Przedluzenie wynajmu garazu ");
                System.out.println("  13. Zwolnij garaz");
                System.out.println("  14. Pokaz wszystkie przedmioty w garazu");
                System.out.println("  15. Dodaj przedmiot do garazu");
                System.out.println("  16. Usun przedmiot z garazu");
                System.out.println("  17. Pokaz wszyskie nieruchomosci wynajmowane przez osobe");
                System.out.println("  18. Pokaz wszystkie przedmioty danej osoby");
                System.out.println("  19. Pokaz dane uzytkownika i wszystkie nieruchomosci i wszystkie przedmioty");
                System.out.println("  20. TEST    TEST   Zapisz aktualny stan OSIEDLA do pliku  TEST");
                System.out.println("  21. EXIT" + '\n');

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

                case "2" -> show(freeRoomsApartmentOrGarage(Room.class));

                case "3" -> show(allRooms(roomSet, Apartment.class));

                case "4" -> show(allRooms(roomSet, Garage.class));

                case "5" -> {
                    try{
                    rentRoom(chooseRoom(Apartment.class), user);}
                    catch(ProblematicTenantException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "6" -> extendRent(chooseUserRoomForExtend(Apartment.class, user),user);

                case "7" -> unRentRoom(chooseUserRoom(Apartment.class, user), user);
                case "8" -> show(personsInApartment(chooseUserRoom(Apartment.class, user)));
                case "9" -> {
                    Optional<Apartment>apartment=chooseUserOptionalRoom(Apartment.class,user);
                    if(apartment.isPresent()){
                        Optional<Person>tempPersonO=chooseOptionalPerson(personsSet);
                        if(tempPersonO.isPresent()) {
                            System.out.println("  OSOBA DO DODANIA " + tempPersonO.get());
                            apartment.get().addPersonToApartment(tempPersonO.get());
                        }
                    }
                }
                case "10" -> {
                    Optional<Apartment>apartment=chooseUserOptionalRoom(Apartment.class,user);
                    if(apartment.isPresent()){
                        Optional<Person>tempPersonO=chooseOptionalPerson(apartment.get().getPersonsInApartment());
                        if(tempPersonO.isPresent()) {
                    if (apartment.get().getPrimaryTenantID().equals(tempPersonO.get().getPesel())) {
                            System.out.println(" nie ma zameldowanych innych osob poza glownym wynajmujacym badz podana osoba jest glownym najemca ! +'\n" +
                                    "W tym momencie nie mozna tej osoby wymeldowac !");
                    break;
                    }
                    apartment.get().removePersonFromApartment(tempPersonO.get());
                    System.out.println(tempPersonO.get() + "  Wymeldowany ");
                        }
                    }
                }

                case "11" ->{
                    try{
                    rentRoom(chooseRoom(Garage.class), user);}catch(ProblematicTenantException e){
                        System.out.println(e.getMessage());
                    }
                }
                case "12" -> extendRent(chooseUserRoomForExtend(Garage.class, user),user);
                case "13" -> unRentRoom(chooseUserRoom(Garage.class, user), user);
                case "14" -> show(itemsInGarage(chooseUserRoom(Garage.class, user)));
                case "15" -> {
                    Garage garage = chooseUserRoom(Garage.class, user);
                    if (garage == null) {
                        System.out.println(" nie mozna dokonac tej operacji !");
                        break;
                    } else {
                        itemTemp = chooseItem(items);
                        System.out.println("  item DO DODANIA " + itemTemp);
                    }
                    if (itemTemp != null) {
                        items.remove(itemTemp);
                        try{
                        garage.addItemsToGarage(itemTemp);}
                        catch(TooManyThingsException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
                case "16" -> {

                    Garage garage = chooseUserRoom(Garage.class, user);
                    if (garage == null) {
                        System.out.println(" nie mozna dokonac tej operacji !");
                        break;
                    } else {
                        itemTemp = chooseItem(garage.getItemsInGarage());

                    }
                    if (itemTemp != null) {

                        items.add(itemTemp);

                        garage.getItemsInGarage().remove(itemTemp);
                        System.out.println("  Przedmiot  " + itemTemp + "  USUNIETY ");
                    }
                }

                case "17" -> {

                    System.out.println(" NIERUCHOMOSCI WYNAJMOWANE PRZEZ UZYTKOWNIKA");
                    show(roomsOfUser(Room.class, user));
                }
                case "18" -> {

                    System.out.println(" WSZYSTKIE PRZEDMIOTY UZYTKOWNIKA");

                    show(allItemsOfUser(user));
                }
                case "19" -> {
                    System.out.println(" DANE UZYTKOWNIKA");
                    System.out.println(user);
                    System.out.println(" NIERUCHOMOSCI WYNAJMOWANE PRZEZ UZYTKOWNIKA");
                    show(roomsOfUser(Room.class, user));
                    System.out.println(" WSZYSTKIE PRZEDMIOTY UZYTKOWNIKA");
                    show(allItemsOfUser(user));
                }

                case "20" -> //   TEST

                    //    TEST
                        extendRent(chooseUserRoomForExtend(Room.class, user),user);

                case "21" -> {
                    System.out.println(" KONIEC PROGRAMU ");
                    //timeInApp.timeRun(this) scheduledExecutorService.shutdown();
                    System.exit(125);
                }

                //00000000000000000000000000000000000
                case "q" -> System.exit(126);
                case "Q" -> System.exit(127);
                //000000000000000000000000000000000000

                default -> {
                    System.out.println(" wrong number ");
                    System.out.println(" try again ");
                }
            }

        }
    }
    public long rentDays(){
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
   return rentDays; }
    private <T extends Room> void extendRent(T room, Person user) {
        if (room != null) {
            for (Map.Entry<UUID, String> entry : estate.entrySet()) {
                if (entry.getKey() == room.getId()) {
                    System.out.println(" podaj ilosc dni do przedluzenia wynajmu :");
                    long  rentDays=rentDays();
                  if (rentDays <= 0) {
                        System.out.println(" liczba dni musi byc wieksza niz zero ! Sprobuj ponownie.");
                    } else {
                        if (room instanceof Apartment apartment) {
                            apartment.setEndRent(new LocalDate[]{timeInApp.getCurrentDate()[0].plusDays(rentDays)});
                            entry.setValue(user.getPesel());
                            apartment.setPrimaryTenantID(user.getPesel());
                            apartment.addPersonToApartment(user);
                            apartment.setStartRent(new LocalDate[]{timeInApp.getCurrentDate()[0]});
                            System.out.println();
                            System.out.println(user + " przedluzyl wynajem  " + room);
                        } else if (room instanceof Garage garage) {
                            garage.setEndRent(new LocalDate[]{LocalDate.now().plusDays(rentDays)});
                            entry.setValue(user.getPesel());
                            garage.setPrimaryTenantID(user.getPesel());
                            garage.setStartRent(new LocalDate[]{LocalDate.now()});
                            System.out.println();
                            System.out.println(user + " przedluzyl wynajem   " + room);
                        }
                    }
                }
            }
                    } else{
                        System.out.println(" prosze prawidlowo wybrac obiekt ! ");
                    }
                }
                public String choose(Scanner scan) {

        System.out.println(" wybierz odpowiedni numer !");
        return scan.nextLine();
    }
    public <T extends Room>List<T> freeRoomsApartmentOrGarage(Class<T>propertyClass) {
        List<T> rooms = new ArrayList<>();
        for (Map.Entry<UUID, String> entry : estate.entrySet()) {
            if(entry.getValue()==null){
                for (Room room : roomSet) {
                    if ((propertyClass.isInstance(room)) && (entry.getKey().equals(room.getId()))) {
                        rooms.add(propertyClass.cast(room));
                    }
                }
            }
        }
        return rooms;
    }
    public <T extends Room> List<T> allRooms(Set<Room> roomsSet, Class<T> propertyClass) {
        List<T> rooms = new ArrayList<>();
        for (Room room : roomsSet) {
            if (propertyClass.isInstance(room)) {
                rooms.add(propertyClass.cast(room));
            }
        }
        return rooms;
    }
    public List<Person> allPersons(Set<Person> persons) {
        return new ArrayList<>(persons);
    }
    public List<Item> allItems(Set<Item> items) {
        return new ArrayList<>(items);
    }
    public List<Item> itemsInGarage(Garage garage) {
        if(garage==null){
            return new ArrayList<>();
        }else{
            if(garage.getItemsInGarage().isEmpty()){
                System.out.println(" Uzytkownik nie posiada zadnych przedmiotow w tym garazu ");
            }
        return new ArrayList<>(garage.getItemsInGarage());
    }
    }
    public List<Person> personsInApartment(Apartment apartment) {
        if(apartment==null){
            return new ArrayList<>();
        }
        return new ArrayList<>(apartment.getPersonsInApartment());
    }
    private List<UUID>getUserRoomIds(Person user){
        List<UUID> ids=new ArrayList<>();
        for (Map.Entry<UUID, String> entry : estate.entrySet()) {
            if (Objects.equals(entry.getValue(), user.getPesel())) {
                ids.add(entry.getKey());
            }
        }
     return ids;
    }
    public <T extends Room> List<T> roomsOfUser(Class<T>propertyClass,Person user){
        List<UUID>ids=getUserRoomIds(user);
        List<T>rooms=new ArrayList<>();

             for(Room room:roomSet){
                 for(UUID id:ids){
                     if((room.getId().equals(id))&&(propertyClass.isInstance(room))){
                         rooms.add((propertyClass.cast(room)));
                     }
                 }
             }
             if(rooms.isEmpty()){
                 System.out.println(" Uzytkownik nie wynaja jeszcze zadnych  nieruchomosci ");
             }

             return rooms;
    }
    public <T extends Room> List<T> roomsOfUserForExtend(Class<T>propertyClass,Person user){
        List<UUID>ids=getUserRoomIds(user);
        List<T>rooms=new ArrayList<>();
        for(Room room:roomSet){
            for(UUID id:ids){
                if((room.getId().equals(id))&&(propertyClass.isInstance(room))&&(room.getStartRent()==null)){
                    rooms.add(propertyClass.cast(room));
                }
            }
        }
        if(rooms.isEmpty()){
            System.out.println(" Uzytkownik nie ma  zaleglosci ");
        }
        return rooms;}
    public Person getPerson(String pesel) {

        Person user = null;
        for (Person person : personsSet) {
            if (pesel.equals(person.getPesel())) {
                user = person;
            }
        }
        return user;
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
    public Optional<Person> chooseOptionalPerson(Set<Person> personsSet) {

        Optional<Person>person= Optional.empty();

        System.out.println(" Prosze podac pesel osoby ktora chcesz wybrac  : " + '\n');
        show(allPersons(personsSet));
        System.out.println();
        String pesel = choose(scan);
        for(Person p:personsSet){
            if(p.getPesel().equals(pesel)){
                person=Optional.ofNullable(p);
            }
        }
   return person;}

    public <T extends Room> T  chooseRoom(Class<T>propertyClass) {

        if (freeRoomsApartmentOrGarage(propertyClass).isEmpty()) {
            System.out.println(" aktualnie brak wolnch pozycji do wynajecia");
            return null;
        }else{
            System.out.println(" Prosze podac numer pod ktorym znajduje sie apartment,ktory chcesz wybrac  : " + '\n');
            show(freeRoomsApartmentOrGarage(propertyClass));
            System.out.println(" numer  :");
            int position = -1;
            try {
                position = (Integer.parseInt(choose(scan))) - 1;

            } catch (NumberFormatException e) {
                System.out.println(" wprowadzono nieprawidlowa liczbe , sprobuj ponownie !");
            }

            if ((position < 0) || (position > freeRoomsApartmentOrGarage(propertyClass).size() - 1)) {
                return null;
            } else {
                System.out.println(freeRoomsApartmentOrGarage(propertyClass).get(position));
                return freeRoomsApartmentOrGarage(propertyClass).get(position);
            }
        }
    }
    public <T extends Room> void rentRoom( T room , Person user) throws ProblematicTenantException {
        try{
            threeFileLimitation(user);
        }catch (ProblematicTenantException e){
            System.out.println(e.getMessage());
        }
        if(!fiveRoomRentLimitation(user)){
            System.out.println(" uzytkownik ma juz na koncie 5 wynajetych obiektow !");
        }else if (room != null) {
                for (Map.Entry<UUID, String> entry : estate.entrySet()) {
                    if (entry.getKey() == room.getId()) {
                        System.out.println(" podaj ilosc dni wynajmu  :");
                        long  rentDays=rentDays();
                        if (rentDays <= 0) {
                            System.out.println(" liczba dni musi byc wieksza niz zero ! Sprobuj ponownie.");
                        } else {
                            if (room instanceof Apartment apartment) {
                                apartment.setEndRent(new LocalDate[]{timeInApp.getCurrentDate()[0].plusDays(rentDays)});
                                entry.setValue(user.getPesel());
                                apartment.setPrimaryTenantID(user.getPesel());
                                apartment.addPersonToApartment(user);
                                apartment.setStartRent(new LocalDate[]{timeInApp.getCurrentDate()[0]});
                                System.out.println();
                                System.out.println(user + " wynaja  " + room);
                            } else if (room instanceof Garage garage) {
                                garage.setEndRent(new LocalDate[]{LocalDate.now().plusDays(rentDays)});
                                entry.setValue(user.getPesel());
                                garage.setPrimaryTenantID(user.getPesel());
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
    public <T extends Room> void unRentRoom( T room , Person user) {

        if (room != null) {
            for (Map.Entry<UUID, String> entry : estate.entrySet()) {
                if (entry.getKey() == room.getId()) {
                    entry.setValue(null);
                    room.setEndRent(new LocalDate[]{null});
                    room.setPrimaryTenantID(null);
                    room.setStartRent(new LocalDate[]{null});
                    if (room instanceof Apartment apartment) {
                        apartment.removePersonFromApartment(user);
                        System.out.println();
                        System.out.println(user + " zwolnil  " + room);
                    }
                    else if (room instanceof Garage garage) {
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



//   OPTIONAL ZAMIAT UZERAC SIE Z NULLLLL   !!!!!!!!


        //                     OPTIONALLLLL

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

    public <T extends Room> Optional<T>  chooseUserOptionalRoom(Class<T>propertyClass,Person user) {

        List<T>rooms=roomsOfUser(propertyClass,user);

        if(rooms.isEmpty()) {
            System.out.println(" user nie ma jeszcze wynajetych zadnych nieruchomosci !");
            return Optional.empty();
        }else {
            System.out.println(" Prosze podac numer pod ktorym znajduje sie nieruchomosc ,ktora chcesz wybrac  : " + '\n');

            show(rooms);

            System.out.println(" numer  :");
                int position;
                try {
                    position = (Integer.parseInt(choose(scan))) - 1;

                } catch (NumberFormatException e) {
                    System.out.println(" wprowadzono nieprawidlowa liczbe , sprobuj ponownie !");
                    return Optional.empty();
                }
                if ((position < 0) || (position >= rooms.size())){

        return Optional.empty();


        } else {
        return Optional.of(rooms.get(position));
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

        return getItem(id,items);
    }
    public Item getItem(UUID itemId,Set<Item>itemsSet) {

        Item item = null;
        for (Item it : itemsSet) {
            if (itemId.equals(it.getId())) {
                item = it;
            }
        }
        return item;
    }

    public boolean fiveRoomRentLimitation(Person user){

        int count=0;

        for(Map.Entry<UUID,String>entry:estate.entrySet()){

            if(Objects.equals(entry.getValue(), user.getPesel())){
                ++count;}
        }
        return count < 5;
    }

    public void threeFileLimitation(Person user) throws ProblematicTenantException{


        if((user.getFiles().size())>3)

        {
            throw new ProblematicTenantException(" osoba "+ user +" ma nieuregulowany wynajem w trzech obiektach");
        }
        System.out.println("  user moze wynajac obiekt, ma mniej niz trzy zadluzenia !");
    }


    public List<Item> allItemsOfUser(Person user){

        List<Item>items=new ArrayList<>();

       List<Garage>tempGarage=roomsOfUser(Garage.class, user).stream().toList();

        // ze wzg na komunikat nie stosuje ponizszej metody
                //roomsOfUser(Garage.class, user).stream().toList();


        for(Garage g:tempGarage){
            items.addAll(g.getItemsInGarage());

        }
        if(items.isEmpty()){
            System.out.println(" uzytkownik nie posiada jeszcze zadnych przedmiotow ");
        }

    /*return tempGarage.stream()
            .map(Garage::getItemsInGarage)
            .flatMap(Set::stream)
            .collect(Collectors.toList());*/
    return items;}



    public void checkEndRent(){

        for(Map.Entry<UUID,String>entry:estate.entrySet()) {
            if (entry.getValue() != null) {


                for (Room room : roomSet) {

                    if (room.getId().equals(entry.getKey())) {

                        if ((timeInApp.currentDate[0].isAfter(room.getEndRent()[0])) && (room.getStartRent() != null)) {

                            room.setStartRent(null);


                            //          UWAGA    zmienilem z 30 dni
                            room.getEndRent()[0] = room.getEndRent()[0].plusDays(10);

                            getPerson(room.getPrimaryTenantID()).getFiles().add(new File(room.getId(),room.getEndRent()[0]));

                        }
                    }
                }
            }

        }

    }

    public void checkLastEndRent() {

        for (Map.Entry<UUID, String> entry : estate.entrySet()) {
            if (entry.getValue() != null) {

                for (Room room : roomSet) {
                    if (room.getId().equals(entry.getKey())) {
                        if ((timeInApp.currentDate[0].isAfter(room.getEndRent()[0])) && (room.getStartRent() == null)) {

                            //ustawienie konca wynajmu
                            room.setEndRent(null);

                           room.setPrimaryTenantID(null);

                           if (room instanceof Garage) {
                                ((Garage) room).clearGarage(items);
                            }
                            if (room instanceof Apartment) {
                                ((Apartment) room).getPersonsInApartment().clear();
                            }

                            //ustawienie w mapie ze nieruchomosc wolna
                            estate.put(room.getId(), null);
                        }
                    }
                }
            }


        }
    }


    public <T extends Room> T  chooseUserRoomForExtend(Class<T>propertyClass,Person user) {

        if (roomsOfUserForExtend(propertyClass, user).size() == 0) {
            System.out.println(" user nie ma  zadnych zaleglosci !");
            return null;
        } else {

            System.out.println(" Prosze podac numer pod ktorym znajduje sie nieruchomosc ,ktora chcesz wybrac  : " + '\n');

            show(roomsOfUserForExtend(propertyClass, user));

            {
                System.out.println(" numer  :");
                int position = -1;
                try {
                    position = (Integer.parseInt(choose(scan))) - 1;

                } catch (NumberFormatException e) {
                    System.out.println(" wprowadzono nieprawidlowa liczbe , sprobuj ponownie !");
                }

                if ((position < 0) || (position > roomsOfUserForExtend(propertyClass, user).size() - 1)) {
                    return null;
                } else {
                    return roomsOfUserForExtend(propertyClass, user).get(position);
                }
            }
        }
    }
}

    // metoda boool  czy osoba wynajmuje mieszkanie  dopiero wtedy moze wynajac garaz !!!!

    /*
    Jeśli najem będzie chciała rozpocząć osoba z więcej niż trzema zadłużeniami (co najmniej 3
            obiekty typu File) na przestrzeni najmów na tle całego osiedla, rzucony powinien zostać wyjątek
    ProblematicTenantException z komunikatem – „Osoba X posiadała już najem pomieszczeń:
    lista_pomieszczen”, gdzie X to imię i nazwisko danej osoby, zaś lista_pomieszczen definiuje wynajmowane pomieszczenia, które zostały wynajęte.
    */

    /*
     W przypadku wkładania każdego przedmiotu lub pojazdu do pomieszczenia musimy się
     upewnić, że pomieszczenie jest w stanie pomieścić ten obiekt. Jeśli tak nie jest, zostaje rzucony
    wyjątek TooManyThingsException z komunikatem „Remove some old items to insert a new
    item”
    */

    /*
    Stan osób zamieszkujących osiedle wraz ze wszelkimi danymi dot. osoby, wynajmowanych
    pomieszczeń, przedmiotów, itp. musi być zapisywane po wybraniu z menu odpowiedniej funkcjonalności. Informacje zapisane w pliku powinny być zapisane przejrzyście i czytelnie dla człowieka
    z zachowaniem poniższych reguł:
            • Pomieszczenia powinny być posortowane rosnąco według rozmiaru pomieszczenia.
            • Zawartość pomieszczenia powinna być posortowana malejąco według rozmiaru przedmiotu
    jeśli jest taki sam to według nazwy.

     */




