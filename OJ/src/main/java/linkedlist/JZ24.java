package linkedlist;

import java.util.LinkedList;

class ListNode {
int val;
ListNode next = null;

ListNode(int val) {
    this.val = val;
}
}

/**
 * 剑指offer 24 翻转链表
 */
public class JZ24 {
    /**
     * 用队列 辅助反转
     * @param head
     * @return
     */
        public ListNode ReverseList(ListNode head) {
            LinkedList<ListNode> q = new LinkedList<>();
            while (head != null) {
                q.add(head);
                head = head.next;
            }

            ListNode out = null;
            while (!q.isEmpty()) {
                ListNode n = q.poll();
                n.next = out;
                out = n;
            }
            return out;
        }

    /**
     * 用双指针
     * @param head
     * @return
     */
    public ListNode ReverseList2(ListNode head) {
        ListNode n1 = null; // 一开始前指针 最前面，空位置处
        ListNode n2 = head; // 后移两个指正，移动的过程中调换连线
        while (n2 != null) {
            ListNode temp = n2.next;
            n2.next = n1;// 调换连线
            n1 = n2;
            n2 = temp;
        }
        return n1;
    }



}
