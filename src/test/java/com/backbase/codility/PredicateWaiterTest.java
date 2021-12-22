package com.backbase.codility;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PredicateWaiterTest {
    @Test(expectedExceptions = {TimeoutException.class, InterruptedException.class})
    public void shouldTimeout() throws InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newWorkStealingPool();
        PredicateWaiter waiter = new PredicateWaiter(executor);

        waiter.waitFor(() -> false, 10, MILLISECONDS, 100, MILLISECONDS);

        executor.shutdown();
        executor.awaitTermination(10, SECONDS);
    }

    @Test
    public void shouldReturnTrue() throws InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newWorkStealingPool();
        PredicateWaiter waiter = new PredicateWaiter(executor);

        waiter.waitFor(() -> true, 10, MILLISECONDS, 100, MILLISECONDS);

        executor.shutdown();
        executor.awaitTermination(10, SECONDS);
    }
}