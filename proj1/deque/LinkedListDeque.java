package deque;

public class LinkedListDeque<T> {
    private class Node {
        public T value;
        public Node prev;
        public Node next;

        public Node(T v, Node p, Node n) {
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
    public void addLast(T item) {
        Node n = sentinel.prev;
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        n.next = sentinel.prev;
        size++;
    }

    /**
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node n = sentinel.next;
        while (n != sentinel) {
            System.out.print(n.value);
            System.out.print(' ');
            n = n.next;
        }
        System.out.println();
    }

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
            return _getRecursive(index, sentinel.next);
        }
    }

    private T _getRecursive(int index, Node n) {
        if (index == 0) {
            return n.value;
        } else {
            return  _getRecursive(index - 1, n.next);
        }
    }
}
