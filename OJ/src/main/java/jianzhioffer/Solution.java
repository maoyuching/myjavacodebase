package jianzhioffer;

import java.util.Stack;

/**
 * 剑指offer 牛客做题记录
 */
public class Solution {

    // 用两个stack 模拟队列
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int ans = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return ans;
    }

    //===========================================================================================
    // 42 连续子数组的最大和
    //===========================================================================================
    /**
     * 动态规划，或者记忆化动态规划
     * @param array 数组
     * @return 连续的子数组的可能的最大和
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
        }
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    // 和上面的dp一样，只是用了记忆化的方法，考虑的上面的dp其实只会看前一个元素，所以用不用dp数组其实不重要
    public int FindGreatestSumOfSubArray2(int[] array) {
        int max = array[0];
        int sum = array[0];
        for (int i = 1; i < array.length; i++) {
            sum = Math.max(sum + array[i], array[i]);
            max = Math.max(max, sum);
        }
        return max;
    }

    //===========================================================================================
    // no.53  数字出现次数，要求 log n 则使用二分法查找
    //===========================================================================================
    public int GetNumberOfK(int [] array , int k) {
        int begin = 0;
        int end = array.length-1;
        // 使用while 循环来二分查找
        // 这里需要使用小于等于号
        while (begin <= end) {
            int m = (begin + end)/2;
            if (array[m] == k){
                int ans = 0;
                for (int i = m; i < array.length; i++) {
                    if (array[i] == k) ans++;
                }
                for (int i = m - 1; i >= 0; i--) {
                    if (array[i] == k) ans++;
                }
                return ans;
            }
            if (array[m] >k) end = m-1;
            else begin = m+1;
        }
        return 0;
    }


    //===========================================================================================
    // no.85  连续子数组最大和
    //===========================================================================================
    public int[] FindGreatestSumOfSubArray3 (int[] array) {
        int begin = 0;
        int end = 0;
        return null;
    }
}