package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int head;
    private int tail;
    private int size;
    private int arraySize;
    private int totalSize;

    public ArrayDeque() {
        size = 0;
        arraySize = 8;
        totalSize = 8 + 2;
        items = (T[]) new Object[totalSize]; // +2, extra spaces for dummy
        head = 0;
        tail = 1;
    }

    @Override
    public void addFirst(T item) {
        if (size >= arraySize) {
            resize(arraySize * 4);
        }
        head = (head - 1 + totalSize) % totalSize;
        items[(head + 1) % totalSize] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size >= arraySize) {
            resize(arraySize * 4);
        }
        tail = (tail + 1) % totalSize;
        items[(tail - 1 + totalSize) % totalSize] = item; // mod could get -1
        size++;
    }

    private void resize(int newArraySize) {
        int newTotalSize = newArraySize + 2;
        T[] newItems = (T[]) new Object[newTotalSize];
        for (int i = head + 1; i <= head + size; i++) {
            newItems[i - head] = items[i % totalSize];
        }
        head = 0;
        tail = size + 1;
        arraySize = newArraySize;
        totalSize = newTotalSize;
        items = newItems;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size > 8 && size * 4 <= totalSize) {
            resize(totalSize / 4);
        }
        if (size == 0) {
            return null;
        }
        T item = items[(head + 1) % totalSize];
        head = (head + 1) % totalSize;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if (size > 8 && size * 4 <= totalSize) {
            resize(totalSize / 4);
        }
        if (size == 0) {
            return null;
        }
        T item = items[(tail - 1 + totalSize) % totalSize];
        tail = (tail - 1 + totalSize) % totalSize;
        size--;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(index + head + 1) % totalSize];
    }

    @Override
    public void printDeque() {
        for (int i = head + 1; i <= head + size; i++) {
            System.out.print(items[i % totalSize]);
            System.out.print(' ');
        }
        System.out.println();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int currIdx = 0;

        @Override
        public boolean hasNext() {
            return currIdx < size;
        }

        @Override
        public T next() {
            currIdx++;
            return get(currIdx - 1);
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Deque) {
            Deque<T> listObj = (Deque<T>) obj;
            if (size() == listObj.size()) {
                Iterator<T> it = iterator();
                Iterator<T> ito = listObj.iterator();

                while (it.hasNext() && ito.hasNext()) {
                    if (it.next() != ito.next()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
