package Environment;


import EstateObjects.Apartment;
import EstateObjects.Garage;
import EstateObjects.Room;
import Items.Item;

import People.Person;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class Environment extends AddRemoveItem{

    public Environment() {
     //super();

        estate = new HashMap<>();
        personsSet = new HashSet<>();
        roomSet = new HashSet<>();
        items = new HashSet<>();

    }


    public Map<UUID, String> getEstate() {
        return estate;
    }

    public void run()  {


        timeRun();

        Person user = null;


        while (true) {

            Apartment apartment=null;
            Garage garage=null;


            String number;

            if (user == null) {
                number = "1";
            } else {

                System.out.println();
                System.out.println("  1.  Pokaz wszystkie osoby / Zaloguj sie ");
                System.out.println("  2.  Pokaz wszystkie wolne mieszkania i garaze");
                System.out.println("  3.  Pokaz wszystkie mieszkania");
                System.out.println("  4.  Pokaz wszystkie garaze");
                System.out.println("  5.  Pokaz wolne mieszkania/ Wynajem mieszkania !");
                System.out.println("  6.  Przedluzenie wynajmu mieszkania ");
                System.out.println("  7.  Zwolnij mieszkanie");
                System.out.println("  8.  Pokaz lokatorow w mieszkaniu");
                System.out.println("  9.  Dodaj lokatora do mieszkania");
                System.out.println("  10. Usun lokatora z mieszkanaia ");
                System.out.println("  11. Pokaz wolne garaze/ Wynajem garazu !");
                System.out.println("  12. Przedluzenie wynajmu garazu ");
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
                   try {
                        rentRoom(chooseRoom(Apartment.class), user);
                    } catch (ProblematicTenantException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "6" -> extendRent(chooseUserRoomForExtend(Apartment.class, user), user);

                case "7" -> unRentRoom(chooseUserRoom(Apartment.class, user), user);

                case "8" -> {

                        apartment=chooseUserRoom(Apartment.class,user);

                        show(personsInApartment(apartment));
                }
                case "9" -> {
                    apartment=chooseUserRoom(Apartment.class,user);
                    addLocatorToApartment(personsSet,apartment);
                }
                case "10" -> {

                    apartment=chooseUserRoom(Apartment.class,user);
                    removeLocatorFromApartment(apartment);
                }

                case "11" -> {
                    try {
                        rentRoom(chooseRoom(Garage.class), user);
                    } catch (ProblematicTenantException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "12" ->

                        extendRent(chooseUserRoomForExtend(Garage.class, user), user);
                case "13" ->

                        unRentRoom(chooseUserRoom(Garage.class, user), user);

                case "14" -> {
                        garage=(chooseUserRoom(Garage.class, user));
                        show(itemsInGarage(garage));}
                case "15" -> {

                    garage=(chooseUserRoom(Garage.class, user));

                    try {
                        addItemToGarage(items,garage);
                    } catch (TooManyThingsException e) {

                        System.out.println(e.getMessage());
                    }
                }

                case "16" -> {

                    garage=(chooseUserRoom(Garage.class, user));
                    removeItemFromGarage(items,garage);
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

                case "20" -> { //   TEST

                    //    TEST  Zapisz aktualny stan OSIEDLA do pliku

                    //timeInApp.timeRunShutDown();

                    // zapis mapy do pliku
                    writeEstateToFile();
                    // zapis setu Person
                    writePersonSetToFile();
                    //writeSetToFile(personsSet);
                    // zapis Setu Room
                    writeRoomSetToFile();
                    // zapis Setu Item
                    writeItemSetToFile();

                    //timeInApp.timeRun(this);

                }
                case "21" -> {
                    System.out.println(" KONIEC PROGRAMU ");
                    timeRunShutDown();
                    scan.close();

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

    public void writeEstateToFile() {
        try
                (BufferedWriter writer = new BufferedWriter(new FileWriter("map.txt"))) {
            for (Map.Entry<UUID, String> entry : estate.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public <T> void writeSetToFile(Set<T> set) {

        String fileName = "" + set + ".txt";
        System.out.println(fileName);
        //String sanitizedFileName = sanitizeFilename(fileName) +".txt";

        try
                (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        //(sanitizedFileName)))
        {
            for (T obj : set) {
                writer.write(obj.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String sanitizeFilename(String name) {
        return name.replaceAll("[^a-zA-Z0-9\\._]+", "_");
    }

    public void writePersonSetToFile() {
        try
                (BufferedWriter writer = new BufferedWriter(new FileWriter("personSet.txt")))

        {
            for (Person p : personsSet) {
                writer.write(p.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void writeRoomSetToFile() {
        try
                (BufferedWriter writer = new BufferedWriter(new FileWriter("roomSet.txt")))

        {
            for (Room r : roomSet) {
                writer.write(r.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    public void writeItemSetToFile() {
        try
                (BufferedWriter writer = new BufferedWriter(new FileWriter("itemSet.txt")))
        {
            for (Item i:items) {
                writer.write(i.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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




