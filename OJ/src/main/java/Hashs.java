import cn.hutool.core.lang.func.Func;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
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

    /**
     * 438 找到字符串字母异位词
     * 纯暴力解法
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        Function<List<String>, Boolean> isSame = (strs) ->{
            StringBuilder s1 = new StringBuilder(strs.get(0)) ;
            StringBuilder s2 = new StringBuilder(strs.get(1)) ;
            if (s1.length() != s2.length()) return false;
            for (char c : s1.toString().toCharArray()) {
                int i = s2.indexOf("" + c );
                if (i > -1){
                    s2.deleteCharAt(i);
                }
            }
            return s2.length() == 0;
        };

        List<Integer> ans = new ArrayList<>();
        int n = p.length();
        for (int i = 0; i < s.length() - n +1; i++) {
            if (isSame.apply(Arrays.asList( s.substring(i, i+n), p))) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 438 找到字符串字母异位词
     * 第二种解法, 滑动窗口，其实上面那个解法和下面这个框架是一样的，只是上面那个isSame 函数里面的
     * indexOf 函数是 O(n) 的，和for p 循环加起来是O(n^2)，所以，导致超时
     * 而这个函数里，是对26长度的数组进行一个for 循环，其实是 O(1) 的
     *
     * 这里的26 length 的数组，当然也可以用哈希表来存储，key就是26个字母，value 就是出现的频次
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        // O(1)
        Function<List<int[]>, Boolean> isSame = (entry)->{
            int[] a1 = entry.get(0);
            int[] a2 = entry.get(1);
            for (int i = 0; i < 26; i++) {
                if (a1[i] != a2[i]) {
                    return false;
                }
            }
            return true;
        };

        if (s.length() < p.length()) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int[] a = new int[26];
        int[] ap = new int[26];
        for (int i = 0; i < p.length(); i++) {
            a[s.charAt(i) - 'a']++;
            ap[p.charAt(i) - 'a']++;
        }
        if (isSame.apply(Arrays.asList(a, ap))) ans.add(0);

        for (int l = 1, r = p.length(); r < s.length(); l++, r++) {
            a[s.charAt(l-1) - 'a']--; // 上一个 字符，删掉
            a[s.charAt(r) - 'a']++;
            if (isSame.apply(Arrays.asList(a, ap))) ans.add(l);
        }
        return ans;
    }

    @Test
    public void test1() {
        Hashs hashs = new Hashs();
        System.out.println(hashs.findAnagrams2("aa", "bb"));
    }
}
