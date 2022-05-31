package leetcode.crackingTheCodingInterview;

import leetcode.ListNode;
import tree.TreeNode;

import java.util.*;

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

    /**
     *节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Queue<Integer> queue = new LinkedList<>();
        for (int[] link : graph) {
            if (link[0] == start) {
                queue.add(link[1]);
            }
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] link : graph) {
            if (map.containsKey(link[0])) {
                map.get(link[0]).add(link[1]);
            } else {
                map.put(link[0], new HashSet<>(Collections.singletonList(link[1])));
            }
        }
        HashSet<Integer> checkedNodes = new HashSet<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == target) {
                return true;
            }
            checkedNodes.add(node);
            // 查找后续节点
            if (map.containsKey(node)) {
                for (Integer i : map.get(node)) {
                    if (!checkedNodes.contains(i)) {
                        queue.add(i);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 分割 链表
     * @param head
     * @param x
     * @return 链表
     */
    public ListNode partition(ListNode head, int x) {
        // 处理边界条件，0个或1个元素
        if (head == null || head.next == null) return head;
        // 记住 一看到链表 就是说双指针
        ListNode p1 = head;
        ListNode p2 = p1.next;
        // travers node if less move to head, else do nothing
        while (p2 != null) {
            if (p2.val < x) {
                // 第一个元素天然有序， 从第二个元素开始，小于x的放到头结点上去
                p1.next = p2.next; // 将这个元素摘出来
                p2.next = head; // 移到头结点
                head = p2; // 更新头结点
                p2 = p1.next; // 移动双指针
            } else {
                // 移动双指针
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return head;
    }

    /**
     * 链表求和
     * 采用递归的方法， 代码不是很漂亮， 主要就是模拟 加法操作，
     * @see this.f()
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return new ListNode(0);
        return f(l1, l2, 0);
    }

    private ListNode f(ListNode l1, ListNode l2, int sum) {
        if (l1 != null) sum += l1.val;
        if (l2 != null) sum += l2.val;
        if (sum == 0 && l1 == null && l2 == null) {
            return null;
        }
        ListNode ans = new ListNode(0);
        if (sum < 10) {
            ans.val = sum;
            ans.next = f(l1 == null ? null: l1.next, l2 == null ? null:l2.next, 0);
        } else {
            ans.val = sum - 10;
            ans.next = f(l1 == null ? null: l1.next, l2 == null ? null:l2.next, 1);
        }
        return ans;
    }

    /**
     * 栈排序
     */
    static class SortedStack {

        Stack<Integer> stack;
        Stack<Integer> stack2;

        public SortedStack() {
            stack = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int val) {
            while (!stack.isEmpty() && stack.peek() < val) {
                stack2.push(stack.pop());
            }
            stack.push(val);
            while (!stack2.isEmpty()) {
                stack.push(stack2.pop());
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        public int peek() {
            if (stack.isEmpty()) return -1;
            return stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    /**
     * 创建含有某一深度上所有节点的链表
     * 树 广度优先搜索 链表 二叉树
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<ListNode> ans = new ArrayList<>();

        if (tree != null) queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode head = new ListNode();
            ListNode listNode = head;
            for (int i = 0; i < size; i++) {
                if (queue.peek().left != null) queue.add(queue.peek().left);
                if (queue.peek().right != null) queue.add(queue.peek().right);
                listNode.next = new ListNode(queue.poll().val);
                listNode = listNode.next;
            }
            ans.add(head.next);
        }
        return ans.toArray(new ListNode[0]);
    }

    /**
     * 合法二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return f2(root.left,null, root) && f2(root.right, root, null);
    }

    /**
     * 辅助递归函数
     * 用一个最大和最小值，表示当前节点数值的应该区间，两边不包括
     * @param root
     * @param min 当前最小节点
     * @param max 当前最大节点
     * @return
     */
    boolean f2(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        return f2(root.left, min, max == null ? root: root.val < max.val ? root: max)
                && f2(root.right, min == null ? root: root.val > min.val ? root: min, max);
    }
}

class StackOfPlates {
    List<Stack<Integer>> container;
    int cap ;

    public StackOfPlates(int cap) {
        this.cap = cap;
    }

    public void push(int val) {
        if (container.size() == 0) container.add(new Stack<>());
        if (container.get(container.size() - 1).size() >= cap) {
            container.add(new Stack<>());
        }
        container.get(container.size() - 1).push(val);
    }

    public int pop() {
        if (container.isEmpty()) return -1;
        if (container.get(container.size()-1).isEmpty()) return -1;
        int ans =  container.get(container.size() - 1).pop();
        if (container.get(container.size() - 1).isEmpty()) {
            container.remove(container.size() - 1);
        }
        return ans;
    }

    public int popAt(int index) {
        if (container.size() -1 < index) return -1;
        return container.get(index).pop();
    }
}
