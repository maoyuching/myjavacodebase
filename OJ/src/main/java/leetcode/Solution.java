package leetcode;

import org.jetbrains.annotations.Contract;

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
