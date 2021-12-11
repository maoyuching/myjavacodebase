package sort;

public abstract class SortHelperSimpleImpl implements SortHelper {


//    public abstract void sort(int[] nums);

    /**
     * 原地swap 数组 member
     * @param n
     * @param i
     * @param j
     */
    void swap(int[] n, int i, int j) {
        int temp = n[i];
        n[i] = n[j];
        n[j] = temp;
    }
}
