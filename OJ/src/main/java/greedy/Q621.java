package greedy;


import lombok.val;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Q621 {
    // 暴力 贪心
    // 引入轮次的概念，每一轮次的任务都是不同的，意味着要执行次数最多的任务的数量就是轮次的数量，而一轮次的大小，只要大于n就满足要求
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.computeIfAbsent(task, c -> 0);
            map.computeIfPresent(task, (c, i) -> i + 1);
        }
        List<Character> l = new ArrayList<>();

        // pick 一个元素 放到任务执行列表
        int i = 0;
        while (map.values().stream().reduce(0, Integer::sum) > 0) {
            List<Map.Entry<Character, Integer>> sortedlist = map.entrySet().stream().filter(e -> e.getValue() > 0).sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue())).collect(Collectors.toList());
            for (Map.Entry<Character, Integer> entry : sortedlist) {
                boolean find = false; // 是否最近执行过
                for (int j = i - 1; j >=0 && j > i - 1 - n; j--) {
                    if (l.get(j).equals(entry.getKey())) {
                        find = true;
                        break;
                    }
                }
                if (find)  continue;// 有重复，找下一个选项
                else{ // 最近没有执行过
                    l.add(entry.getKey());
                    map.put(entry.getKey(), entry.getValue() - 1);
                    break; // 找到了任务，放进列表，OK，i++，找下一个要执行的任务
                }
            }
            if (l.size() <= i) l.add(' '); // 实在没有找到，放一个空任务
            i++;
        }
        return l.size();
    }

    // 把这道题编程数学题了
    public int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char task : tasks) {
            map.computeIfAbsent(task, c -> 0);
            map.computeIfPresent(task, (c, i) -> i + 1);
        }
        List<Map.Entry<Character, Integer>> sortedlist = map.entrySet().stream().filter(e -> e.getValue() > 0).sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue())).collect(Collectors.toList());
        // 上面还是一样
        // 轮次
        int rounds = sortedlist.get(0).getValue();
        // 必须要在每一个轮次里执行的任务的数量，
        int taskNumberMustBeInARound = (int) sortedlist.stream().filter(e -> e.getValue().equals(rounds)).count();
        // rounds - 1 表示必然会执行完全的轮次，最后一轮次则是不用的，只需要执行 taskNumberMustBeInARound 次就可以了
        // 这里有个特殊点，就是每一个轮次可以大于n，意味着，只要不是必须在每一轮次执行的任务，都可以扩展某些轮次的长度即可
        return Math.max((rounds - 1) * n + taskNumberMustBeInARound, tasks.length);
    }

    @Test
    public void t1() {
        char[] characters = new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'};
        Q621 q = new Q621();
        val ans = q.leastInterval(characters, 2);
        System.out.println(ans);
    }
}
