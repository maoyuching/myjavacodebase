package leetcode;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    static Solution s = new Solution();

    @Test
    public void multiply() {
        int n1 = 123;
        int n2 = 456;
        System.out.println(s.multiply(String.valueOf(n1), String.valueOf(n2)));
        Assert.assertEquals(String.valueOf(n1 * n2),s.multiply(String.valueOf(n1), String.valueOf(n2)));
    }

    @Test
    public void addStrings() {
        int n1 = 3456;
        int n2 = 123;
        System.out.println(s.addStrings(String.valueOf(n1), String.valueOf(n2)));
        Assert.assertEquals(String.valueOf(n1 + n2),s.addStrings(String.valueOf(n1), String.valueOf(n2)));
    }
}