package leetcode.msjd;

import leetcode.ListNode;

/**
 * 程序员面试经典
 */
public class Solution2 {

    /**
     * 是否回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // 找到中间位置的节点 ex. 12345 mid:2
        int midlen = len / 2 ;
        ListNode midnode = head;
        for (int i = 0; i < midlen-1; i++) {
            midnode = midnode.next;
        }
        // ex 1 -> 2 ==> 1 <- 2
        ListNode nod = midnode.next;
        while (nod != null) {
            ListNode temp = nod.next;
            nod.next = midnode;
            midnode = nod;
            nod = temp;
        }
        // 判断 是否回文
        for (int i = 0; i < len / 2; i++) {
            if (head.val != tail.val) return false;
            head = head.next;
            tail = tail.next;
        }
        return true;
    }
}
