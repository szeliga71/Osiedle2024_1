package Environment;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeInApp {

    public Environment en;

    final LocalDate[] currentTime = {LocalDate.now()};


    public TimeInApp(Environment en) {
        this.en = en;
    }

    public LocalDate[] getCurrentTime() {
        return currentTime;
    }

    public void timeRun(Environment en) {


        //pula watkow 3 watki
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        //watek 1 - symulacja czasu

        Runnable time = () -> {
            currentTime[0] = currentTime[0].plusDays(1);

            // to pokazuje aktualny czas w konsoli
            System.out.println(currentTime[0]);
        };

        // watek 2 - sprawdzanie daty konca wynajmu
        Runnable check1 = () -> {

        };
        scheduledExecutorService.scheduleAtFixedRate(time, 0, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(check1, 0, 10, TimeUnit.SECONDS);
        }
    }

