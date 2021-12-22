package com.backbase.codility;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

/**
 * Codility task for a new QA
 */
public class PredicateWaiter {
    ExecutorService executor;

    public PredicateWaiter(ExecutorService executor) {
        this.executor = executor;
    }

    public void waitFor(Supplier<Boolean> supplier, int poolingTime, TimeUnit poolingUnit, int timeout,
                        TimeUnit timeoutUnit) throws TimeoutException {
        try {
            executor.submit(() -> {
                while (true) {
                    if (supplier.get()) {
                        return;
                    }
                    try {
                        poolingUnit.sleep(poolingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }).get(timeout, timeoutUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
