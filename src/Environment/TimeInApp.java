package Environment;

import EstateObjects.Apartment;
import EstateObjects.Garage;
import EstateObjects.Room;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeInApp {

    public Environment en;

    final LocalDate[] currentDate = {LocalDate.now()};


    public TimeInApp(Environment en) {
        this.en = en;
    }

    public LocalDate[] getCurrentDate() {
        return currentDate;
    }

    public void timeRun(Environment en) {


        //pula watkow 3 watki
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        //watek 1 - symulacja czasu

        Runnable timeSimulation = () -> {
            currentDate[0] = currentDate[0].plusDays(1);

            // to pokazuje aktualny czas w konsoli
            System.out.println(currentDate[0]);
        };

        // watek 2 - sprawdzanie daty konca wynajmu
        Runnable check1 = () -> {

            System.out.println(" czy weszlo do check 1 ");



         /*   for(Room r: en.roomSet){
                if((currentDate[0].isAfter(r.getEndRent()[0]))&&(r.getStartRent()!=null)){

                    System.out.println(" war 1");
                    r.setStartRent(null);

                    en.getPerson(r.getPrimaryTenantID()).getFiles().add(new File(r.getId()));

                    //ponizej ustawienie 30 dniowego okresu karencji przed wyczyszczeniem pomieszczenia
                    r.getEndRent()[0]=r.getEndRent()[0].plusDays(30);

                }

            }*/

        };

        //watek 3   sprawdzenie jesli wynajem nie odnowiony czyszczenie pomieszczenia  file w aktach
        Runnable check2 = () -> {

            System.out.println(" czy weszlo do check 2 ");

           /* for(Room r: en.roomSet) {
                if ((currentDate[0].isAfter(r.getEndRent()[0])) && (r.getStartRent() == null)) {

                    System.out.println(" war 2");


                    //usuniecie roboczego file
                    for(File f:en.getPerson(r.getPrimaryTenantID()).getFiles()){
                        if((f.roomId.equals(r.getId()))&&(f.creationType.equals("UUID"))){
                            en.getPerson(r.getPrimaryTenantID()).getFiles().remove(f);
                        }
                    }

                    //nowe file na trwale
                    en.getPerson(r.getPrimaryTenantID()).getFiles().add(new File(r.getId(), r.getEndRent()[0]));
                    //ustawienie konca wynajmu
                    r.setEndRent(null);

                    r.setPrimaryTenantID(null);

                    if(r instanceof Garage) {
                        Garage garage = (Garage) r;
                        ((Garage) r).clearGarage(en.items);
                    }
                    if(r instanceof Apartment) {
                         Apartment apartment = (Apartment) r;
                        ((Apartment) r).getPersonsInApartment().clear();
                    }


                    if(Apartment.class.isInstance(r)){

                    }
                    //ustawienie w mapie ze nieruchomosc wolna
                    en.estate.put(r.getId(),null);
                }
            }


            */
        };


        scheduledExecutorService.scheduleAtFixedRate(timeSimulation, 0, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check1, 0, 10, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check2, 0, 20, TimeUnit.SECONDS);
        }
    }

