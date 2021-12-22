package com.backbase.codility;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.fail;

/**
 * Unit test for EvictingQueue
 */
public class EvictingQueueTest {
    @Test
    public void shouldRemoveFirstItemWhenAddingMaxPlusOneElement() {
        int items = 101;
        EvictingQueue<Object> queue = new EvictingQueue<>();
        for (int i = 0; i <= items; i++) {
            queue.addElement(i);
        }
        assertThat(queue.pollAll().getLast())
                .as("Not 101")
                .isEqualTo(items);
    }

    @Test
    public void shouldHandleConcurrentUsers() throws InterruptedException {
        final int threadPool = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadPool);
        EvictingQueue<Object> queue = new EvictingQueue<>();
        int items = 101;
        Runnable runnable = () -> {
            for (int i = 0; i < items; i++) {
                try {
                    queue.addElement(i);
                } catch (Exception e) {
                    fail(e.toString());
                }
            }
        };

        for (int i = 0; i < threadPool; i++) {
            executor.execute(runnable);
        }
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        int size = queue.pollAll().size();
        assertThat(size)
                .as("wrong size")
                .isEqualTo(100);
    }
}
