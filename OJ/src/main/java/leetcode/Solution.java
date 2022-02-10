package leetcode;

import tree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 题集合
 *
 * @author myq
 * date 2022-02-03
 */
public class Solution {
    /**
     * 43 字符串相乘
     * 依赖415 字符串 add function,
     * 如果直接强转int，也是通不过的，有的数字特别大，超过int的范围，同时也不能用大数，只能自己模拟
     * @param num1
     * @param num2
     * @return ans
     */
    public String multiply(String num1, String num2) {
        // handle special status
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        String ans = "";
        // use two for-loop , getting sub ans
        for (int i = num1.length()-1, x = 0; i >= 0; i--, x++) {
            for (int j = num2.length()-1, y = 0; j >=0 ; j--, y++) {
                // ---------------------- use addString() !!!
                int res = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                StringBuilder sb = new StringBuilder(res + "");
                for (int k = 0; k < x + y; k++) {
                    sb.append("0");
                }
                ans = addStrings(ans, sb.toString());
                // ----------------------
            }
        }
        return ans;
    }

    /**
     * 89 格雷编码
     * 一开始直接用下面那几个辅助函数，用回溯法来做，的确应该是可以的，但是实际不可行，n=10就运行不下去了。
     * 还得用这种聪明的，找规律的办法，哎
     * @param n
     * @return ans
     */
    public List<Integer> grayCode(int n) {
        if (n == 1) {
            return new ArrayList<>(Arrays.asList(0, 1));
        }
        List<Integer> ans = grayCode(n - 1);
        for (int i = ans.size()-1; i >=0 ; i--) {
            ans.add((1 << (n - 1)) + ans.get(i));
        }
        return ans;
    }

    /**
     * 89 格雷编码 辅助函数
     * @param arr 全排列的数组
     * @param index 表示在决定下标为index的元素
     */
    boolean f(int[] arr, int index) {
        // 找到了
        if (index >= arr.length) {
            return isGray(arr[0], arr[arr.length - 1]);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : arr) list.add(i);
        list.add(list.get(0));
        boolean isgray = true;
        for (int i = 0; i < list.size()-1; i++) {
            if (!isGray(list.get(i), list.get(i + 1))) {
                isgray = false;
            }
        }
        if (isgray) return true;
        // 否则 继续寻找
        for (int i = index; i < arr.length; i++) {
            // 如果满足一部分条件，继续寻找
            if (isGray(arr[i], arr[index-1])) {
                // 换一个位置
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
                // 递归寻找
                boolean find = f(arr, index+1);
                if (find) {
                    return true;
                }
                // 换回来
                temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
        return false;
    }

    /**
     * 86 格雷编码辅助 函数
     */
    public boolean isGray(int a, int b) {
        int c = a ^ b;
        if (c == 0) return false;
        if (c == 1) return true;
        while (c != 1) {
            if (c % 2 !=0) return false;
            c /= 2;
        }
        return true;
    }

    /**
     * 230 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        // use stack to get all element in binary tree
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> traced = new HashSet<>();
        stack.push(root);
        traced.add(root);
        while (!stack.isEmpty()) {
            TreeNode t = stack.peek();
            if (t.left != null && !traced.contains(t.left)) {
                stack.push(t.left);
            } else {
                list.add(t.val);
                stack.pop();
                traced.add(t);
                if (t.right != null) {
                    stack.push(t.right);
                }
            }
        }
        return list.get(k - 1);
    }
    /**
     * 415 字符串相加
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
            StringBuffer sb = new StringBuffer();

            int carry = 0;
            //记录进位，carry 将会是一个不大于20 的数
            int i1 = num1.length()-1;
            // i1 is the pointer of num1 which starts from end to begin
            int i2 = num2.length()-1;  // likewise

            while (i1 >= 0 || i2 >= 0 || carry != 0) {
                // 还没有遍历完以及，还有进位
                if(i1 >= 0) carry += num1.charAt(i1--) - '0';
                // ascii subset to get the int value of char
                if(i2 >= 0) carry += num2.charAt(i2--) - '0';
                // i2--, it will first use i2, then do --, it's tricky

                sb.append(carry % 10);
                //　mod 10 to get 个位数
                carry /= 10;
                // floor div 10 to get 十位数(0 or 1)
            }
            return sb.reverse().toString();
            // 由于 一开始是append的，所以一开始就是反的.
    }

}
