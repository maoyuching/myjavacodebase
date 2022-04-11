package leetcode.crackingTheCodingInterview;

//请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(
//1)。 示例： MinStack minStack = new MinStack(); minStack.push(-2); minStack.push(0);
// minStack.push(-3); minStack.getMin();   --> 返回 -3. minStack.pop(); minStack.
//top();      --> 返回 0. minStack.getMin();   --> 返回 -2. Related Topics 栈 设计 👍 66 👎
//0

import java.util.Stack;

/**
 * i did not find out the way to keep o(1) on push()......
 * until I looked the web page
 * use a sync min stack that always keep the min value of now peek()......
 * very smart , very stupid I am;
 */
class MinStack {

    Stack<Integer> ms;
    Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        ms = new Stack<>();

    }

    public void push(int x) {
        stack.push(x);
        if (ms.isEmpty()) ms.push(x);
        else ms.push(Math.min(x, ms.peek()));
    }

    public void pop() {
        int x = stack.pop();
        ms.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return ms.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)
