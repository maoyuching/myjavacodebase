package sort;

public class ShellSort extends SortHelperSimpleImpl {

    /**
     * 希尔排序
     * 对插入排序的改良，仔细看，他就是在插入排序外层加了一层 分组
     * 最坏的情况是 O n^2
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
