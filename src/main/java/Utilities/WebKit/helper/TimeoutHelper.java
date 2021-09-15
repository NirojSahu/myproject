//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.helper;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimeoutHelper {
    private TimeoutHelper() {
    }

    public static void withTimeout(Integer timeout, Runnable runnable) throws TimeoutException {
        ExecutorService scheduledExecutorService = Executors.newSingleThreadExecutor();

        try {
            scheduledExecutorService.submit(runnable).get((long)timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        } catch (ExecutionException var5) {
        }

    }
}
