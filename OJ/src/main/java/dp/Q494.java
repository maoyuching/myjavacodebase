package dp;

import org.junit.Test;

/**
 * 目标和  LeetCode 494
 */
public class Q494 {
    int ans = 0;

    public int findTargetSumWays(int[] nums, int target) {
        f(nums, 0, 0, target);
        return ans;
    }

    void f(int[] nums, int index, int sum, int target) {
        // 结算时刻
        if (index == nums.length) {
            if (sum == target) ans++;
        }else{
            int i = nums[index];
            f(nums, index+1, sum+i, target);
            f(nums, index+1, sum-i, target);
        }
    }
    @Test
    public void test1() {
        Q494 q = new Q494();

        int[] a = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        int ans = q.findTargetSumWays(a, target);
        System.out.println( ans);
    }
}
