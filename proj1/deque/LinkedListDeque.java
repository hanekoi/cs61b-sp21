package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private T value;
        private Node prev;
        private Node next;

        Node(T v, Node p, Node n) {
            value = v;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel; // form a loop
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * @param item The item you wish to add to the deque.
     */
    @Override
    public void addFirst(T item) {
        Node n = sentinel.next;
        sentinel.next = new Node(item, sentinel, sentinel.next);
        n.prev = sentinel.next; // take care of the next one!
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item The item you wish to add to the deque.
     */
    @Override
    public void addLast(T item) {
        Node n = sentinel.prev;
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        n.next = sentinel.prev;
        size++;
    }

    /**
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        Node n = sentinel.next;
        while (n != sentinel) {
            System.out.print(n.value);
            System.out.print(' ');
            n = n.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node n = sentinel.next;
            sentinel.next = n.next;
            n.next.prev = sentinel;
            size--;
            return n.value;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node n = sentinel.prev;
            sentinel.prev = n.prev;
            n.prev.next = sentinel;
            size--;
            return n.value;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            Node n = sentinel;
            for (int i = 0; i <= index; i++) {
                n = n.next;
            }
            return n.value;
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            return getRecursiveHelper(index, sentinel.next);
        }
    }

    private T getRecursiveHelper(int index, Node n) {
        if (index == 0) {
            return n.value;
        } else {
            return  getRecursiveHelper(index - 1, n.next);
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node currNode = sentinel.next;

        @Override
        public boolean hasNext() {
            return currNode != sentinel;
        }

        @Override
        public T next() {
            T currVal = currNode.value;
            currNode = currNode.next;
            return currVal;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Deque ) {
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
