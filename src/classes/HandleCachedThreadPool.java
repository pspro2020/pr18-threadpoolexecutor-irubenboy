package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandleCachedThreadPool {
    private final ThreadPoolExecutor cachedThreadPool =
            (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private final DateTimeFormatter f = DateTimeFormatter.ofPattern("mm:ss:ms");

    public void execute(Power power){
        try {
            cachedThreadPool.execute(power);
            System.out.printf("%s -> Handle -> Thread pool size: %d\n", LocalDateTime.now().format(f)
                    , cachedThreadPool.getPoolSize());
            System.out.printf("%s -> Handel -> Active threads count: %d\n",
                    LocalDateTime.now().format(f), cachedThreadPool.getActiveCount());
        } catch (Exception e) {
            System.out.printf("%s -> Handle -> Task rejected : %s\n",
                    LocalDateTime.now().format(f), power.getName());
        }
    }

    public void shoutDown() throws InterruptedException {
        cachedThreadPool.shutdown();
        if(cachedThreadPool.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.printf("%s -> Handle terminated. Completed : %d\n",
                    LocalDateTime.now().format(f), cachedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("%s -< Handle Await termination timeout. Completed: %d\n",
                    LocalDateTime.now().format(f), cachedThreadPool.getCompletedTaskCount());
        }
    }
}
