package Environment;


import EstateObjects.Apartment;
import EstateObjects.Garage;
import EstateObjects.Room;


import java.time.LocalDate;
import java.util.Map;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class TimeInApp extends RoomService{



    ScheduledExecutorService scheduledExecutorService;

    final LocalDate[] currentDate = {LocalDate.now()};


    public TimeInApp() {
        scheduledExecutorService=Executors.newScheduledThreadPool(3);
    }


    public LocalDate[] getCurrentDate() {
        return currentDate;
    }

    public void timeRunShutDown(){
        this.scheduledExecutorService.shutdown();

    }

    public void timeRun() {

        Runnable timeSimulation = () -> {
            currentDate[0] = currentDate[0].plusDays(1);

        };


        Runnable check1 = () -> {
            checkEndRent();

        };


        Runnable check2 = () -> {

            checkLastEndRent();


        };

        scheduledExecutorService.scheduleAtFixedRate(timeSimulation, 0, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check1, 0, 10, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check2, 0, 10, TimeUnit.SECONDS);
        }


    public void checkEndRent() {

       for (Map.Entry<UUID, String> entry : getEstate().entrySet()) {
            if (entry.getValue() != null) {


                for (Room room : getRoomSet()) {

                    if (room.getId().equals(entry.getKey())) {

                        if ((currentDate[0].isAfter(room.getEndRent()[0])) && (room.getStartRent() != null)) {

                            room.setStartRent(null);

                            room.getEndRent()[0] = room.getEndRent()[0].plusDays(30);

                            getPerson(room.getPrimaryTenantID()).getFiles().add(new File(room.getId(), room.getEndRent()[0]));

                        }
                    }
                }
            }

        }

    }

    public void checkLastEndRent() {

       for (Map.Entry<UUID, String> entry : getEstate().entrySet()) {
            if (entry.getValue() != null) {

                for (Room room : getRoomSet()) {
                    if (room.getId().equals(entry.getKey())) {
                        if ((currentDate[0].isAfter(room.getEndRent()[0])) && (room.getStartRent() == null)) {

                            room.setEndRent(null);

                            room.setPrimaryTenantID(null);

                            if (room instanceof Garage garageLocal) {
                                garageLocal.clearGarage(getItems());
                                garageLocal.addItemFromGarageToGlobal(getItems());

                            }
                            if (room instanceof Apartment) {
                                ((Apartment) room).getPersonsInApartment().clear();
                            }

                            getEstate().put(room.getId(), null);
                        }
                    }
                }
            }


        }
    }
    }

