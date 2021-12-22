package com.backbase.codility;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Codility task for a new QA
 */
public class EvictingQueue<E> {
    public static final int MAX_ELEMENTS = 100;
    private AtomicReference<Deque<E>> dequeReference;

    public EvictingQueue() {
        dequeReference = new AtomicReference<>(new LinkedList<E>());
    }

    public synchronized void addElement(E element) {
        Deque<E> deque = dequeReference.get();
        if (deque.size() < MAX_ELEMENTS) {
            deque.add(element);
        } else {
            deque.remove();
            deque.add(element);
        }
    }

    public Deque<E> pollAll() {
        return dequeReference.getAndSet(new LinkedList<E>());
    }
}
