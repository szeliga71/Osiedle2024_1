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
           //System.out.println(currentDate[0]);
        };

        // watek 2 - sprawdzanie daty konca wynajmu
        Runnable check1 = () -> {
            en.checkEndRent();

        };

        //watek 3   sprawdzenie jesli wynajem nie odnowiony czyszczenie pomieszczenia  file w aktach
        Runnable check2 = () -> {


            en.checkLastEndRent();
            //System.out.println(" czy weszlo do check 2 ");

        };


        scheduledExecutorService.scheduleAtFixedRate(timeSimulation, 0, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check1, 0, 10, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check2, 0, 10, TimeUnit.SECONDS);
        }
    }

