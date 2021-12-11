package sort;

/**
 * 用自己的理解来写希尔排序
 */
public class MyShellSort extends SortHelperSimpleImpl {

    @Override
    public void sort(int[] nums) {
        // 第一步，分组，这里gap表示组间元素距离，其实，也表示了组数
        // 最终组数都要归到 1, 也就是对整个数组做插入排序, 所以在gap到1之前，所做的就是在宏观上将各组排序，
        // 这样，到最终插入排序的时候，很多元素不用再移动, 插入排序最好的时候可以是 On
        for (int gap = nums.length / 2; gap > 0; gap /= 2) {
            // 对 每一组，做一次插入排序
            for (int i = 0; i < gap; i++) {
                // 可以看到，插入排序是写2个for循环， 下面就是
                for (int j = i + gap; j < nums.length; j += gap) {
                    int temp = nums[j]; // 先暂存当前的元素
                    int x;
                    for (x = j; x > 0 && nums[x - gap] > temp; x -= gap) {
                            nums[x] = nums[x - gap]; // 往后稍稍
                    }
                    nums[x] = temp; // 做插入动作
                }
            }
        }
    }
}
