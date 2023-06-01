/**
 * FILE		    : IQueue.java
 * AUTHOR		: Nuri Can OZTURK
 * LAST UPDATE	: 26.05.2023
 * IQueue interface is source for LinkedList Queue implementation.
 * Copyleft (c) DSA-Lib
 * All Rights Free
 */
package org.example;

import java.util.Optional;

public interface IQueue<T>
{
    /**
     * Get first element from Linked List. İf empty, return Optional.Empty().
     *
     * @return Optional<T> if not empty Linked List.
     */
    Optional<T> peek();

    /**
     * Remove first element in the queue
     *
     * @return removed element wrapped with Optional
     */
    Optional<T> dequeue();

    /**
     * enqueue element to queue
     *
     * @param item your item
     */
    void enqueue(T item);

    /**
     * @return true if full else return false
     */
    boolean isFull();

    /**
     * @return true if empty else return false
     */
    boolean isEmpty();
}
