package com.finalproject.TimSort;

public class SortItem<T> {
    final T element;
    final int index;
    final long value;

    SortItem(T element, int index, long value) {
        this.element = element;
        this.index = index;
        this.value = value;
    }

    long getValue() { return value; }
}
