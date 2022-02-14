package leetcode;

import org.junit.Assert;
import org.junit.Test;
import tree.TreeNode;

import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {

    static Solution s = new Solution();

    // a simple binary search tree
    static TreeNode root1 = new TreeNode(2);
    static {
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
    }

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

    @Test
    public void kthSmallest() {
        Assert.assertEquals(2, s.kthSmallest(root1, 2));
    }

    @Test
    public void grayCode() {
        int n = 2;
        List<Integer> ans = s.grayCode(n);
        System.out.println(ans);

        for (int i = 0; i < ans.size() - 1; i++) {
            int a = ans.get(i);
            int b = ans.get(i + 1);
            assertTrue(s.isGray(a,b));
        }
        int a = ans.get(0);
        int b = ans.get(ans.size() - 1);
        assertTrue(s.isGray(a,b));
    }

    @Test
    public void isGray() {
        assertTrue(s.isGray(0,1));
        assertTrue(s.isGray(8,0));
        assertFalse(s.isGray(3,4));
    }

    @Test
    public void rotateRight() {

    }

    @Test
    public void f() {
    }

    @Test
    public void spiralOrder() {
        int[][] m = new int[][]{{1, 2, 3,4}, {5, 6, 7,8}, {9, 10, 11, 12},{13, 14, 15,16}};
        int[][] m1 = new int[][]{{1, 2, 3,4}, {5, 6, 7,8}, {9, 10, 11, 12}};
        int[][] m2 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7,8,9}};
        List<Integer> ans = s.spiralOrder(m1);
        ans.stream().map(i -> i + " ").forEach(System.out::print);
        System.out.println();
        s.spiralOrder(m2).stream().map(i -> i + " ").forEach(System.out::print);
        System.out.println();
        s.spiralOrder(m).stream().map(i -> i + " ").forEach(System.out::print);
    }
}