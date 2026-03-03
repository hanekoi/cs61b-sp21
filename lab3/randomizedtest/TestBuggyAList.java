package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> listA = new AListNoResizing<>();
        BuggyAList<Integer> listB = new BuggyAList<>();

        for(int i = 4; i <= 6; i++) {
            listA.addLast(i);
            listB.addLast(i);
        }

        assertEquals(listA.removeLast(), listB.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> LB = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                LB.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeB = LB.size();
                assertEquals(size, sizeB);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() != 0) {
                    int last = L.getLast();
                    int lastB = LB.getLast();
                    assertEquals(last, lastB);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() != 0) {
                    int last = L.removeLast();
                    int lastB = LB.removeLast();
                    assertEquals(last, lastB);
                }
            }
        }
    }
}
