package sort;

public class InsertSort extends SortHelperSimpleImpl {
    /**
     * 复杂度：O(n^2)
     * 如果已经排序，则为O(n)
     * @param nums
     */
    @Override
    public void sort(int[] nums) {
        // 第一个元素天然有序，所以从第二个开始
        for (int i = 1; i < nums.length; i++) {
            // 将元素移动到他所在的位置，向前找第一个小于它的元素，也就是跳过所有比他大的元素
            int temp = nums[i]; // 可以用swap，也可以这样，先将元素暂存起来
            int j = i;
            for (; j > 0; j--) {
                if (nums[j-1] > temp) {
                     nums[j] = nums[j-1];
                }else break;
            }
            nums[j] = temp;
        }
    }
}
