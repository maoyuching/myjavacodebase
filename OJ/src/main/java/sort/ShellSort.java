package sort;

public class ShellSort extends SortHelperSimpleImpl {

    /**
     * 希尔排序
     * @param nums
     */
    @Override
    public void sort(int[] nums) {
       int j ;
        for (int gap = nums.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < nums.length; i++) {
                int tmp = nums[i];
                for (j = i; j >= gap && tmp < nums[j - gap]; j -= gap) {
                    nums[j] = nums[j - gap];
                }
                nums[j] = tmp;
            }
        }
    }
}
