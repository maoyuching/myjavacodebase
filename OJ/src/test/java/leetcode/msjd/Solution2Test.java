package leetcode.msjd;

import leetcode.ListNode;
import lombok.val;
import org.junit.Test;

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
}