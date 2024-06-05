package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        container[size()] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        modCount++;
        Objects.checkIndex(index, size);
        T value = get(index);
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        modCount++;
        Objects.checkIndex(index, size);
        T del = get(index);
        System.arraycopy(
                container, // откуда копируем
                index + 1, // начиная с какого индекса
                container, // куда копируем
                index, // начиная с какого индекса
                container.length - index - 1 // сколько элементов копируем
        );
        // на последнее место ставим null, чтобы не было утечки памяти (если удаляем последний элемент)
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
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int index = 0;
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
