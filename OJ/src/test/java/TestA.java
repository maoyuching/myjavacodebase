import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 给你一个不同学生的分数列表，请按 学生的 id 顺序 返回每个学生 最高的五科 成绩的 平均分。
 * 对于每条 items[i] 记录， items[i][0] 为学生的 id，items[i][1] 为学生的分数。平均分请采用整数除法计算。
 *
 *
 * 示例：
 *
 * 输入：[[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * 输出：[[1,87],[2,88]]
 * 解释：
 * id = 1 的学生平均分为 87。
 * id = 2 的学生平均分为 88.6。但由于整数除法的缘故，平均分会被转换为 88。
 *
 *
 * 提示：
 *
 * * 1 <= items.length <= 1000
 * * items[i].length == 2
 * * 学生的 ID 在 1 到 1000 之间
 * * 学生的分数在 1 到 100 之间
 * * 每个学生至少有五个分数
 */
public class TestA {
    @Test
    public void test() {
        TestA a = new TestA();
//        int[][] ints = new int[][]{new int[]{1, 91}, new int[]{1, 92}, new int[]{2,59}};
        int[][] ints = new int[] []{{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}};
        System.out.println(JSONUtil.toJsonStr(a.f(ints)));
    }
    int[][] f(int[][] arg) {
        // 第一个元素为学生id 第二个元素为成绩
        // map < id, 成绩列表>
        // treemap
        // value 优先队列
        Map<Integer, List<Integer>> degree = new HashMap<>();
        for (int[] ints : arg) {
            if (degree.containsKey(ints[0])) {
                degree.get(ints[0]).add(ints[1]);
            } else {
                degree.put(ints[0], Lists.newArrayList(ints[1]));
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        //
        for (Integer i : degree.keySet()) {
            double d = degree.get(i).stream()
                    .sorted(Comparator.reverseOrder())
                    .limit(5)
                    .mapToInt(Integer::intValue)
                    .average()
                    .getAsDouble();
            ans.add(Arrays.asList(i, (int) d));
        }
        // 按照
        Collections.sort(ans, Comparator.comparing(l -> l.get(0)));
        int[][] ret = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ret[i][0] = ans.get(i).get(0);
            ret[i][1] = ans.get(i).get(1);
        }
        return ret;
    }

    // 两个请求 1. get redis 2 read db 3 update redis
    //        1.  del redis 2 write db
    //

    // 构造 autowired postco
    // bean 多例模式
    // map key -value
    Map<Integer, Integer> map = new ConcurrentHashMap<>();

    // double check
    int f2(Integer k, Integer v) {
        if (!map.containsKey(k)) {
            synchronized (this) {
                if (!map.containsKey(k)) {
                    map.put(k, v);
                }
            }
        }
        return map.get(k);
    }
}
