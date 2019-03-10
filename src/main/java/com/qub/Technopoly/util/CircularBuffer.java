package com.qub.Technopoly.util;

import static java.lang.reflect.Array.newInstance;

public class CircularBuffer<T> {

    public final int length;

    private T[] buffer;
    private int currentIndex = 0;
    private int currentSize = 0;

    public CircularBuffer(Class<T> clazz, int size) {
        buffer = (T[]) newInstance(clazz, size);
        length = buffer.length;
    }

    // TODO - Check currentSize, or Throw.
    public void add(T newT) {
        buffer[currentSize] = newT;
        currentSize++;
    }

    public T getNext() {
        var next = buffer[currentIndex];

        currentIndex++;
        if (currentIndex >= buffer.length) {
            currentIndex = 0;
        }

        return next;
    }

    public void setCurrentPosition(int position) {
        currentIndex = position % buffer.length;
    }

    public int getCurrentPosition() {
        var currentPos = currentIndex - 1;
        if (currentPos < 0) {
            currentPos = buffer.length - 1;
        }
        return currentPos;
    }

    public T[] getBuffer() {
        return buffer;
    }
}
