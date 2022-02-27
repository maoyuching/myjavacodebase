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

    /**
     * 链表相交,找到两个链表的相交点, 思路先判断两个链表的长度，然后让长的那个先走几步，以至于同一起跑线，再去判断重复节点即可
     * @param headA
     * @param headB
     * @return 相交的节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp = headA;
        int lena = 0;
        while (temp != null) {
            lena++;
            temp = temp.next;
        }
        temp = headB;
        int lenb = 0;
        while (temp != null) {
            lenb++;
            temp = temp.next;
        }
        for (int i = 0; i < lena - lenb; i++) {
            headA = headA.next;
        }
        for (int i = 0; i < lenb - lena; i++) {
            headB = headB.next;
        }
        while (headA != null && headB != null) {
            if (headA == headB) { // 注意这里要直接判断节点是同一个
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 链表 环路检测
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null) return null;
            if (fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        // now s = f
        ListNode temp = new ListNode(1, head);
        while (temp != slow) {
            slow = slow.next;
            temp = temp.next;
        }
        return temp;
    }
}
