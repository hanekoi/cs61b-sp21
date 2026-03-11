package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        return max(comp);
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }

        T currMax = get(0);
        for (int i = 0; i < size(); i++) {
            T currItem = get(i);
            currMax = comp.compare(currMax, currItem) > 0 ? currMax : currItem;
        }
        return currMax;
    }
}

