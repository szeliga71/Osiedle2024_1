package Environment;

public class TimeInApp {

}
/*
import java.util.Scanner;

public class ProgramInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        // Thread to listen for the interrupt command
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Press 'q' to quit.");
                String input = scanner.nextLine();
                if ("q".equals(input)) {
                    System.out.println("Quitting program...");
                    mainThread.interrupt();
                    break;
                }
            }
            scanner.close();
        }).start();

        try {
            // Main program loop
            while (!Thread.interrupted()) {
                // Your program logic here
                System.out.println("Program running... Press 'q' to stop.");
                Thread.sleep(1000); // Simulate some operation
            }
        } catch (InterruptedException e) {
            System.out.println("Program interrupted!");
        }
    }
}
 */