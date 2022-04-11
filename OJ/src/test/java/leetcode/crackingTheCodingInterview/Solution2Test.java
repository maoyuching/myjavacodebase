package leetcode.crackingTheCodingInterview;

import leetcode.ListNode;
import lombok.val;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class Solution2Test {

    Solution2 s2 = new Solution2();

    /**
     * 构造链表
     * @param nums
     * @return 链表头结点
     */
    public static ListNode genLn(int... nums) {
        if (nums.length == 0) return null;
        val head = new ListNode(nums[0]);
        ListNode temp = head;
        for (int i = 1; i < nums.length; i++) {
            temp.next = new ListNode(nums[i]);
            temp = temp.next;
        }
        return head;
    }

    @Test
    public void isPalindrome() {
        assertEquals(false, s2.isPalindrome(genLn(1,1,2,1)));
        assertEquals(true, s2.isPalindrome(genLn(1)));
        assertEquals(false, s2.isPalindrome(genLn(1,2)));
    }

    @Test
    public void getIntersectionNode() {
        val la = genLn(1, 2, 3, 4, 5, 6);
        val lb = genLn(9);
        lb.next = la.next;
        assertEquals(2,s2.getIntersectionNode(la, lb).val);
    }

    @Test
    public void detectCycle() {
        val l = genLn(1, 2, 3, 4, 5);
        l.next.next.next.next.next = l.next;
//        assertEquals(2, s2.detectCycle(l).val);

        val l2 = genLn(1);
        assertNull(s2.detectCycle(l2));
        Toolkit.getDefaultToolkit().beep();
    }

    @Test
    public void findWhetherExistsPath() {
        int[][] graph = new int[4][2];
        graph[0] = new int[]{0, 1};
        graph[1] = new int[]{0, 2};
        graph[2] = new int[]{1, 2};
        graph[3] = new int[]{1, 2};
        val b = s2.findWhetherExistsPath(3, graph, 0, 2);
        assertTrue(b);
        assertFalse(s2.findWhetherExistsPath(3,graph,2,1));
    }
}