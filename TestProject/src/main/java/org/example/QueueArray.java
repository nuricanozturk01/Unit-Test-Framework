/**
 * FILE		    : QueueArray.java
 * AUTHOR		: Nuri Can OZTURK
 * LAST UPDATE	: 28.05.2023
 * QueueArray class represent the queue
 * Copyleft (c) DSA-Lib
 * All Rights Free
 */
package org.example;


import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class QueueArray<T> implements IQueue<T> {
    private final T[] m_queueArr;
    private final int m_capacity;
    private int m_rear;
    private int m_front;
    private int m_size;

    @SuppressWarnings("unchecked")
    public QueueArray(int capacity) {
        m_queueArr = (T[]) new Object[capacity];
        m_capacity = capacity;
        m_rear = 0;
        m_front = 0;
        m_size = 0;
    }

    public QueueArray() {
        this(10);
    }

    public int getSize() {
        return m_size;
    }

    @Override
    public Optional<T> peek() {
        return isEmpty() ? empty() : of(m_queueArr[m_front]);
    }

    @Override
    public Optional<T> dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue has not element...");

        var element = m_queueArr[m_front];

        if (element == null)
            return empty();

        m_queueArr[m_front++] = null;
        m_size--;
        return of(element);
    }

    @Override
    public void enqueue(T item) {
        if (isFull())
            throw new QueueFullException("Queue is full!");
        m_queueArr[m_rear++] = item;
        m_size++;
    }

    @Override
    public boolean isFull() {
        return m_size >= m_capacity;
    }

    @Override
    public boolean isEmpty() {
        return m_queueArr[m_front] == null;
    }
}
