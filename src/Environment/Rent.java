package Environment;

import EstateObjects.Apartment;
import EstateObjects.Garage;
import EstateObjects.Room;
import People.Person;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public abstract class Rent extends TimeInApp {




    public <T extends Room> void rentRoom(T room, Person user) throws ProblematicTenantException {
        try {
            threeFileLimitation(user);
        } catch (ProblematicTenantException e) {
            System.out.println(e.getMessage());
        }
        if (!fiveRoomRentLimitation(user)) {
            System.out.println(" uzytkownik ma juz na koncie 5 wynajetych obiektow !");
        } else if (room != null) {
            for (Map.Entry<UUID, String> entry : getEstate().entrySet()) {
                if (entry.getKey() == room.getId()) {
                    System.out.println(" podaj ilosc dni wynajmu  :");
                    long rentDays = rentDays();
                    if (rentDays <= 0) {
                        System.out.println(" liczba dni musi byc wieksza niz zero ! Sprobuj ponownie.");
                    } else {
                        if (room instanceof Apartment apartment) {
                            apartment.setEndRent(new LocalDate[]{getCurrentDate()[0].plusDays(rentDays)});
                            entry.setValue(user.getPesel());
                            apartment.setPrimaryTenantID(user.getPesel());
                            apartment.addPersonToApartment(user);
                            apartment.setStartRent(new LocalDate[]{getCurrentDate()[0]});
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

    public <T extends Room> void unRentRoom(T room, Person user) {

        if (room != null) {
            for (Map.Entry<UUID, String> entry : getEstate().entrySet()) {
                if (entry.getKey() == room.getId()) {
                    entry.setValue(null);
                    room.setEndRent(new LocalDate[]{null});
                    room.setPrimaryTenantID(null);
                    room.setStartRent(new LocalDate[]{null});
                    if (room instanceof Apartment apartment) {
                        apartment.removePersonFromApartment(user);
                        System.out.println();
                        System.out.println(user + " zwolnil  " + room);
                    } else if (room instanceof Garage garage) {
                        garage.clearGarage(getItems());
                        System.out.println();
                        System.out.println(user + " zwolnil  " + room);
                    }
                }
            }
        } else {
            System.out.println(" prosze prawidlowo wybrac obiekt ! ");
        }
    }

    public boolean fiveRoomRentLimitation(Person user) {

        int count = 0;

        for (Map.Entry<UUID, String> entry : getEstate().entrySet()) {

            if (Objects.equals(entry.getValue(), user.getPesel())) {
                ++count;
            }
        }
        return count < 5;
    }

    public void threeFileLimitation(Person user) throws ProblematicTenantException {


        if ((user.getFiles().size()) > 3) {
            throw new ProblematicTenantException(" osoba " + user + " ma nieuregulowany wynajem w trzech obiektach");
        }
        System.out.println("  user moze wynajac obiekt, ma mniej niz trzy zadluzenia !");
    }

    public long rentDays() {
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
        return rentDays;
    }
}
