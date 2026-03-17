package tester;

import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;
import edu.princeton.cs.introcs.StdRandom;

public class TestArrayDequeEC {
    @Test
    public void HelloTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < 500; i++) {
            int choice = StdRandom.uniform(4);

            switch (choice) {
                case 0:
                    sad.addFirst(i);
                    ads.addFirst(i);
                    message.append("addFirst(");
                    message.append(i);
                    message.append(")\n");
                    assertEquals(message.toString(), sad.get(0), ads.get(0));
                    break;
                case 1:
                    sad.addLast(i);
                    ads.addLast(i);
                    message.append("addLast(");
                    message.append(i);
                    message.append(")\n");
                    assertEquals(message.toString(), sad.get(ads.size() - 1), ads.get(ads.size() - 1));
                    break;
                case 2:
                    if (!ads.isEmpty()) {
                        Integer n1 = sad.removeFirst();
                        Integer n2 = ads.removeFirst();
                        message.append("removeFirst()\n");
                        assertEquals(message.toString(), n1, n2);
                    }
                    break;
                case 3:
                    if (!ads.isEmpty()) {
                        Integer n1 = sad.removeLast();
                        Integer n2 = ads.removeLast();
                        message.append("removeLast()\n");
                        assertEquals(message.toString(), n1, n2);
                    }
                    break;
            }
        }
    }
}
