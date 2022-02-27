import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Array {

    /**
     * 406 根据身高重建队列
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        people = Arrays.stream(people)
                .sorted((p1, p2) -> {
                    int h1 = p1[0], h2 = p2[0];
                    if (h1 < h2) return -1;
                    if (h1 > h2) return 1;
                    return p2[1] - p1[1];
                })
                .toArray(int[][]::new);

        int[][] ans = new int[people.length][2];

        List<Integer> slots = IntStream.range(0, people.length).boxed().collect(Collectors.toList());

        for (int[] entry : people) {
            int k = entry[1];
            ans[slots.get(k)] = entry;
            slots.remove(k);
        }

        return ans;
    }

    /**
     * 416 分割等和子集
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
//        List<Integer> coll = new LinkedList<>();
//        Arrays.sort(nums);
//        return f(coll, nums, 0, new HashMap<>());
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        int half = sum / 2;
        Arrays.sort(nums);
        if (nums[nums.length-1] > half) return false;

        int[] dp = new int[half + 1];
        dp[0] = 1;
        for (int x : nums) {
            dp[x] = 1;
            for (int i = half; i > 0; i--) {
                if (i-x > 0 && dp[i-x] == 1) dp[i] = 1;
            }
        }
        return dp[half] == 1;
    }

    boolean f(List<Integer> coll, int[] nums, int i, HashMap<String, Integer> cache) {
        int half;
        if (cache.containsKey(getKey(coll))) {
            half = cache.get(getKey(coll));
        } else {
            half = coll.stream().reduce(Integer::sum).orElse(0);
            cache.put(getKey(coll), half);
        }
        int sum = Arrays.stream(nums).sum();

        if (sum % 2 == 1) return false;
        if (half == sum/2) return true;
        if (i >= nums.length) return false;

        if (half < sum / 2) {
            boolean b1 = f(coll, nums, i + 1, cache);
            coll.add(nums[i]);
            boolean b2 = f(coll, nums, i + 1, cache);
            coll.remove(coll.size() - 1);
            return b1 || b2;
        }
        return false;
    }

    String getKey(List<Integer> coll) {
        StringBuilder sb = new StringBuilder("0");
        coll.forEach(s -> sb.append("-").append(s));
        return sb.toString();
    }
}















