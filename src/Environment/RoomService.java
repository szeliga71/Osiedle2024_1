package Environment;

import EstateObjects.Room;
import People.Person;

import java.util.*;

public abstract class RoomService extends Util{
    public <T extends Room> T chooseUserRoom(Class<T> propertyClass, Person user) {

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

    public <T extends Room> List<T> freeRoomsApartmentOrGarage(Class<T> propertyClass) {
        List<T> rooms = new ArrayList<>();
        for (Map.Entry<UUID, String> entry : estate.entrySet()) {
            if (entry.getValue() == null) {
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

    public <T extends Room> List<T> roomsOfUser(Class<T> propertyClass, Person user) {
        List<UUID> ids = getUserRoomIds(user);


        List<T> rooms = new ArrayList<>();



        for (Room room : getRoomSet()) {
            for (UUID id : ids) {
                if ((room.getId().equals(id)) && (propertyClass.isInstance(room))) {
                    rooms.add((propertyClass.cast(room)));
                }
            }
        }
        if (rooms.isEmpty()) {
            System.out.println(" Uzytkownik nie wynaja jeszcze zadnych  nieruchomosci ");
        }

        return rooms;
    }



    public <T extends Room> T chooseRoom(Class<T> propertyClass) {

        if (freeRoomsApartmentOrGarage(propertyClass).isEmpty()) {
            System.out.println(" aktualnie brak wolnch pozycji do wynajecia");
            return null;
        } else {
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
    protected List<UUID> getUserRoomIds(Person user) {
        List<UUID> ids = new ArrayList<>();
        for (Map.Entry<UUID, String> entry : estate.entrySet()) {
            if (Objects.equals(entry.getValue(), user.getPesel())) {
                ids.add(entry.getKey());
            }
        }
        return ids;
    }

}
