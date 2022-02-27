package huawei;

import java.util.*;
import java.util.stream.Collectors;

public class Huawei3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] jobs = sc.nextLine().split(",");
        int time = Integer.parseInt(sc.nextLine());

        // 任务调度器，核心代码其实就是四行，哎，难受，想了半个小时没想出来
        // 每个轮次就是一个冷冻时间加一， 然后一共轮次最多就是最多的任务的数量
        // 最重要的思想是, 然后一些多出来的任务，可以任意附加在任何轮次上，一个伦次不是必然n+1，可能大于他
        Map<String, Integer> map = Arrays.stream(jobs).collect(Collectors.toMap(j -> j, j -> 1, Integer::sum));
        int max = map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
        long sum = map.entrySet().stream().filter(e -> e.getValue() >= max).count();
        long ans = Math.max(sum + (long) (max - 1) * (time + 1), jobs.length);

        System.out.println(ans);

    }
}
