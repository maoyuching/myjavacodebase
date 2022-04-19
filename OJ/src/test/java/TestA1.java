import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * zhouyanchun z00478851对所有人说说： 05:45 PM
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * class Solution {
 *     public int[] searchRange(int[] nums, int target) {
 *
 *     }
 * }
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class TestA1 {

    // 二分搜索
    public int[] searchRange2(int[] nums, int target) {
        // 边界条件
        if (null == nums || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int begin = 0;
        int end = nums.length - 1;
        List<Integer> ans = new ArrayList<>();
        while (begin < end) {
            int middle = (begin + end) / 2;
            if (nums[middle] < target) {
                begin = middle + 1;
            } else if (nums[middle] > target) {
                end = middle - 1;
            } else {
                for (int i = middle; i >= 0; i--) {
                    if (nums[i] == target) {
                        ans.add(i);
                    }else {
                        break;
                    }
                }
                for (int i = middle+1; i < nums.length; i++) {
                    if (nums[i] == target) {
                        ans.add(i);
                    }else {
                        break;
                    }
                }
                int[] res = new int[ans.size()];
                for (int i = 0; i < ans.size(); i++) {
                    res[i] = ans.get(i);
                }
                return res;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 搜索range
     * @param nums
     * @param target
     * @return
     */
        public int[] searchRange(int[] nums, int target) {
         // 边界条件
         if (null == nums || nums.length == 0) {
             return new int[]{-1, -1};
         }
         List<Integer> ans = new ArrayList<>();
         for (Integer i = 0; i < nums.length; i++) {
             if (nums[i] == target) {
                 ans.add(i);
             }
             if (nums[i] > target) {
                 break;
             }
         }
         if ( ans.isEmpty()) {
             return new int[]{-1, -1};
         }

         //
         int[] res = new int[ans.size()];
         for (int i = 0; i < ans.size(); i++) {
             res[i] = ans.get(i);
         }
         return res;
     }

     @Test
    public void test1() {
         TestA1 test = new TestA1();
         System.out.println(Arrays.toString(test.searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 8)));
         System.out.println(Arrays.toString(test.searchRange2(new int[]{5, 7, 7, 8, 8, 10}, 6)));
         System.out.println(Arrays.toString(test.searchRange2(new int[]{}, 0)));
     }
}
