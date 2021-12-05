
import java.util.*;

public class Graph {

    class Link{ // 表示后续节点, 的连线
        String next; double value;
        Link(String n, double v) {
            this.next = n; this.value = v;
        }
    }

    double f(Map<String, List<Link>> map, String from, String to, Map<String, Boolean> search) {
        if (map.containsKey(from) && map.containsKey(to) && search.get(from)) {
            // 直接查找，后续节点
            if (from.equals(to)) return 1.0;
            for (Link link : map.get(from)) {
                if (link.next.equals(to)) {
                    return link.value;
                }
            }
            search.put(from, false); // 已经搜索过，就不要再搜索了,否则会无限循环
            // 委托后续节点查找, 需要递归
            for (Link link : map.get(from)) {
                double ans = f(map, link.next, to, search);
                if (ans > -0.0) { // 说明存在link, 直接就算找到吧
                    return link.value * ans;
                }
            }
        }
        return -1.0;
    }
    /**
     * 399 除法求值
     * 构建一个map，key为
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        double[] ans = new double[queries.size()];

        // 构造一个 图 用 hash 表存储 node -> (nextNode, linkValue)
        // 人为地将其构造为双链表, 按照题目要求
        Map<String, List<Link>> mapNode2Link = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            if (!mapNode2Link.containsKey(eq.get(0))) {
                mapNode2Link.put(eq.get(0), new ArrayList<Link>());
            }
            if (!mapNode2Link.containsKey(eq.get(1))) {
                mapNode2Link.put(eq.get(1), new ArrayList<Link>());
            }
            mapNode2Link.get(eq.get(0)).add(new Link(eq.get(1), values[i]));
            mapNode2Link.get(eq.get(1)).add(new Link(eq.get(0), 1.0/values[i]));
        }
        // 求解, 只需要将参数放进递归函数中查找即可
        for (int i = 0; i < queries.size();i++) {
            String x = queries.get(i).get(0) , y = queries.get(i).get(1);
            Map<String, Boolean> search = new HashMap<>();
            for (String key : mapNode2Link.keySet()) search.put(key, true);
            double v = f(mapNode2Link, x, y, search);
            ans[i] = v;
        }
        return ans;
    }
}
