package leetcode.crackingTheCodingInterview;

//三合一。描述如何只用一个数组来实现三个栈。
//
// 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。
//stackNum表示栈下标，value表示压入的值。
//
// 构造函数会传入一个stackSize参数，代表每个栈的大小。
//
// 示例1:
//
//
// 输入：
//["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
//[[1], [0, 1], [0, 2], [0], [0], [0], [0]]
// 输出：
//[null, null, null, 1, -1, -1, true]
//说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
//
//
// 示例2:
//
//
// 输入：
//["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
//[[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
// 输出：
//[null, null, null, null, 2, 1, -1, -1]
//
//
//
//
// 提示：
//
//
// 0 <= stackNum <= 2
//
// Related Topics 栈 设计 数组 👍 49 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class TripleInOne {

    int[] arr;
    int stackSize;
    int[] index;

    public TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        arr = new int[3 * stackSize];
        index = new int[3];
        index[0] = 0;
        index[1] = stackSize;
        index[2] = 2 *stackSize;
    }

    public void push(int stackNum, int value) {
        if (isFull(stackNum))return;
        arr[index[stackNum]++] = value;
    }

    public int pop(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        int ans = arr[--index[stackNum]];
        return ans;
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        return arr[index[stackNum]-1];
    }

    public boolean isEmpty(int stackNum) {
        return index[stackNum] == stackNum * stackSize;
    }

    boolean isFull(int stackNum) {
        return index[stackNum] == (stackNum + 1) * stackSize;
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */
//leetcode submit region end(Prohibit modification and deletion)


