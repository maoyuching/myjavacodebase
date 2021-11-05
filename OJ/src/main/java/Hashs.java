import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hashs {

    /**
     * ## 128
     * @param nums
     * @return length max
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>(IntStream.of(nums).boxed().collect(Collectors.toList()));
        return s.stream().filter(i -> !s.contains(i - 1)).map(i -> {
            int j = 0;
            while (s.contains(i++)) j++;
            return j;
        }).max(Integer::compare).orElse(0);

    }

    /**
     * see method: longestConsecutive
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        // 使用 hash set 以达到On 的时效性
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {set.add(num);}

        int ans = 0;
        for (int i : set) {
            int temp = 0;
            // 找到一个起点, 比如4， 如果数组中有3，那4就不能当做起点
            if (!set.contains(i-1)) {
                while (set.contains(i++)) {
                    temp++;
                }
            }
            ans = Math.max(ans, temp);
        }

        return ans;
    }
}
