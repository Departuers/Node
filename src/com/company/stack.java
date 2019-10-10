package com.company;

public interface stack<T> {
    int getsize();

    void push(T element);

    T pop();

    T peek();

    boolean isEmpty();
}
