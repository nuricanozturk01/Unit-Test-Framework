package org.example;

public class QueueFullException extends RuntimeException{
    public QueueFullException(String message) {
        super(message);
    }
}
