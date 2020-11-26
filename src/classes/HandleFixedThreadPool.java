package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HandleFixedThreadPool {
    private final int NUMBER_PROCESSORS =  Runtime.getRuntime().availableProcessors();
    private final ThreadPoolExecutor fixedThreadPool =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(NUMBER_PROCESSORS);
    private final DateTimeFormatter f = DateTimeFormatter.ofPattern("mm:ss:ms");

    public void execute(Power p){
        try {
            fixedThreadPool.execute(p);
            System.out.printf("%s -> Handle -> Thread pool size: %d\n", LocalDateTime.now().format(f)
                    , fixedThreadPool.getPoolSize());
            System.out.printf("%s -> Handle -> Active threads count: %d\n",
                    LocalDateTime.now().format(f), fixedThreadPool.getActiveCount());
        } catch (RejectedExecutionException e) {
            System.out.printf("%s -> Handle -> Task rejected: %s\n",
                    LocalDateTime.now().format(f), fixedThreadPool.getPoolSize());
        }
    }

    public void shutDown() throws InterruptedException {
        fixedThreadPool.shutdown();
        if(fixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.printf("%s -> Handle terminated. Completed: %d\n",
                    LocalDateTime.now().format(f), fixedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("%s -> Handle await termination timeout. Complete: %d\n",
                    LocalDateTime.now().format(f), fixedThreadPool.getCompletedTaskCount());
        }
    }
}
