package campusconnect.generics;

import java.util.Iterator;

public class CustomArrayList<T> {

    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements = new Object[DEFAULT_CAPACITY];

    public void add(T item) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = item;
        size++;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        Object[] arr = new Object[newCapacity];
        for (int i = 0; i < elements.length; i++) {
            arr[i] = elements[i];
        }
        elements = arr;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
        return (T) elements[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

