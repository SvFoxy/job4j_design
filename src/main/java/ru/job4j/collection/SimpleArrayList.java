package ru.job4j.collection;


import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void checkLength() {
        if (size == container.length && container.length > 0) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        }
    }
    @Override
    public void add(T value) {
        checkLength();
        modCount++;
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T value = get(index);
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T del = get(index);
        modCount++;
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        container[container.length - 1] = null;
        size--;
        return del;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}
