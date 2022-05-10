package leetcode.crackingTheCodingInterview;

import org.junit.Test;

import static org.junit.Assert.*;

public class TripleInOneTest {

    @Test
    public void test1() {
        TripleInOne stack = new TripleInOne(18);

        assertEquals(-1, stack.peek(1));
        assertTrue(stack.isEmpty(1));
        stack.push(1, 50);
        stack.push(1, 40);

        assertEquals(40, stack.pop(1));
        assertEquals(50, stack.peek(1));
        assertEquals(50, stack.pop(1));
        assertTrue(stack.isEmpty(1));

    }

    @Test
    public void test2() {
        TripleInOne stack = new TripleInOne(1);
        stack.push(0,1);
        stack.push(0,2);

        assertEquals(1, stack.pop(0));
        assertTrue(stack.isEmpty(0));
    }

}