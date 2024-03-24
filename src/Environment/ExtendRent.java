package Environment;

import EstateObjects.Apartment;
import EstateObjects.Garage;
import EstateObjects.Room;
import People.Person;

import java.time.LocalDate;
import java.util.*;

public abstract class ExtendRent extends Rent {


    public <T extends Room> List<T> roomsOfUserForExtend(Class<T> propertyClass, Person user) {
        List<UUID> ids = getUserRoomIds(user);
        List<T> rooms = new ArrayList<>();
        for (Room room : getRoomSet()) {
            for (UUID id : ids) {
                if ((room.getId().equals(id)) && (propertyClass.isInstance(room)) && (room.getStartRent() == null)) {
                    rooms.add(propertyClass.cast(room));
                }
            }
        }
        if (rooms.isEmpty()) {
            System.out.println(" Uzytkownik nie ma  zaleglosci ");
        }
        return rooms;
    }

    public <T extends Room> T chooseUserRoomForExtend(Class<T> propertyClass, Person user) {

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


    protected <T extends Room> void extendRent(T room, Person user) {
        if (room != null) {
            for (Map.Entry<UUID, String> entry : estate.entrySet()) {
                if (entry.getKey() == room.getId()) {
                    System.out.println(" podaj ilosc dni do przedluzenia wynajmu :");
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
        } else {
            System.out.println(" prosze prawidlowo wybrac obiekt ! ");
        }
    }
}
