/**
 * FILE		    : StackArray.java
 * AUTHOR		: Nuri Can OZTURK
 * LAST UPDATE	: 28.05.2023
 * StackArray class represent the stack
 * Copyleft (c) DSA-Lib
 * All Rights Free
 */
package org.example;


import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class StackArray<T> implements IStack<T> {
    private final T[] m_stackArr;
    private final int m_capacity;
    private int m_size;
    private int m_top;

    public StackArray() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public StackArray(int capacity) {
        m_capacity = capacity;
        m_size = 0;
        m_top = -1;
        m_stackArr = (T[]) new Object[capacity];
    }

    @Override
    public Optional<T> peek() {
        return isEmpty() ? empty() : of(m_stackArr[m_top]);
    }

    public int getSize() {
        return m_size;
    }

    @Override
    public Optional<T> pop() {

        if (isEmpty())
            throw new NoSuchElementException("Stack has not any element...");

        var item = m_stackArr[m_top];

        m_stackArr[m_top--] = null;
        m_size--;

        return of(item);
    }

    @Override
    public boolean isEmpty() {
        return m_size == 0;
    }

    @Override
    public boolean isFull() {
        return m_size == m_capacity;
    }

    @Override
    public void push(T item) {
        if (isFull())
            throw new StackFullException("Stack is full");

        m_stackArr[++m_top] = item;
        m_size++;
    }
}
