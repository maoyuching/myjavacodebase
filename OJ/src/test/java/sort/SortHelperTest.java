package sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortHelperTest {

    static int[] nums = new int[]{3, 2, 1,5,4,8,7,6};
    static int[] numsSorted = new int[]{1, 2, 3,4,5,6,7,8};


    @Test
    public void test1() {
        SortHelper sortHelper = new InsertSort();
        sortHelper.sort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    @Test
    public void test2() {
        SortHelper sortHelper = new MyShellSort();
        sortHelper.sort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}