package deque;

public class ArrayDeque<T> {
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

    public void addFirst(T item) {
        if (size >= arraySize) {
            resize(arraySize * 4);
        }
        head = (head - 1 + totalSize) % totalSize;
        items[(head + 1) % totalSize] = item;
        size++;
    }

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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

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

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(index + head + 1) % totalSize];
    }

    public void printDeque() {
        for (int i = head + 1; i <= head + size; i++) {
            System.out.print(items[i % totalSize]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
