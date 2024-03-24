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

    public Environment en;


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


        //pula watkow 3 watki
        //ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        //watek 1 - symulacja czasu

        Runnable timeSimulation = () -> {
            currentDate[0] = currentDate[0].plusDays(1);

            // to pokazuje aktualny czas w konsoli
           //System.out.println(currentDate[0]);
        };

        // watek 2 - sprawdzanie daty konca wynajmu
        Runnable check1 = this::checkEndRent;

        //watek 3   sprawdzenie jesli wynajem nie odnowiony czyszczenie pomieszczenia  file w aktach
        //System.out.println(" czy weszlo do check 2 ");
        Runnable check2 = this::checkLastEndRent;


        scheduledExecutorService.scheduleAtFixedRate(timeSimulation, 0, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check1, 0, 10, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check2, 0, 10, TimeUnit.SECONDS);
        }


    public void checkEndRent() {

        for (Map.Entry<UUID, String> entry : en.getEstate().entrySet()) {
            if (entry.getValue() != null) {


                for (Room room : en.getRoomSet()) {

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

        for (Map.Entry<UUID, String> entry : en.getEstate().entrySet()) {
            if (entry.getValue() != null) {

                for (Room room : en.getRoomSet()) {
                    if (room.getId().equals(entry.getKey())) {
                        if ((currentDate[0].isAfter(room.getEndRent()[0])) && (room.getStartRent() == null)) {

                            //ustawienie konca wynajmu
                            room.setEndRent(null);

                            room.setPrimaryTenantID(null);

                            if (room instanceof Garage garageLocal) {
                                garageLocal.clearGarage(en.getItems());
                                garageLocal.addItemFromGarageToGlobal(en.getItems());

                            }
                            if (room instanceof Apartment) {
                                ((Apartment) room).getPersonsInApartment().clear();
                            }

                            //ustawienie w mapie ze nieruchomosc wolna
                            en.getEstate().put(room.getId(), null);
                        }
                    }
                }
            }


        }
    }
    }

