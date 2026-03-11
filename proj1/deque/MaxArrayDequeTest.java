package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

// package private
class IntComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 > o2) {
            return 1;
        } else if (o1 == o2) {
            return 0;
        } else {
            return -1;
        }
    }
}

class Dog {
    private int age;

    Dog(int a) {
        age = a;
    }

    public int age() {
        return age;
    }
}

class DogAgeComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog o1, Dog o2) {
        if (o1.age() > o2.age()) {
            return 1;
        } else if (o1.age() == o2.age()) {
            return 0;
        } else {
            return -1;
        }
    }
}

public class MaxArrayDequeTest {
    @Test
    public void IntMaxTest() {
        IntComparator ic = new IntComparator();
        MaxArrayDeque<Integer> ma = new MaxArrayDeque<>(ic);

        for(int i = 0; i < 10; i++) {
            ma.addLast(i);
        }

        assertEquals(9, (int) ma.max());

        for(int i = 0; i < 10; i++) {
            ma.removeLast();
        }

        assertEquals(null, ma.max());
    }

    @Test
    public void DogTest() {
        DogAgeComparator dc = new DogAgeComparator();
        MaxArrayDeque<Dog> ma = new MaxArrayDeque<>(dc);

        int maxAge = 0;
        for (int i = 0; i < 1000; i++) {
            int a = StdRandom.uniform(15);
            maxAge = Math.max(maxAge, a);
            Dog d = new Dog(a);

            ma.addLast(d);
        }

        assertEquals(maxAge, ma.max().age());
    }
}
