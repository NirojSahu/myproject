package Utilities.WebKit;

import java.util.concurrent.*;

public class TimeoutHelper {
    private TimeoutHelper(){
    }
    public static void withTimeout(Integer timeout,Runnable runnable) {
        ExecutorService sceduledExecutorService = Executors.newSingleThreadExecutor();
        try {
            sceduledExecutorService.submit(runnable).get((long) timeout, TimeUnit.MICROSECONDS);
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        } catch (ExecutionException var5) {
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
