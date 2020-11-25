package classes;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Power implements Runnable{
    private final String name;
    private final int number;
    private final DateTimeFormatter f = DateTimeFormatter.ofPattern("mm:ss:ms");

    public Power(int number){
        this.number = number;
        this.name = "Power of " + number;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s -> %s: %d ^ %d = %.0f\n",
                    LocalDateTime.now().format(f), Thread.currentThread()
                    .getName(), number, i, Math.pow(number, i));
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
