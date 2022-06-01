import java.util.Arrays;
import java.util.List;

/**
 * 2022 06 01
 */

public class Solution {

    public int getMatchedManagers(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        boolean[] visit = new boolean[g.length];
        boolean[] gived = new boolean[s.length];
        for (int i = 0; i < s.length; i++) {
            for (int i1 = 0; i1 < g.length; i1++) {
                if (!visit[i1] && !gived[i] && s[i] >= g[i1]) {
                    visit[i1] = true;
                    gived[i] = true;
                    ans ++;
                }
            }
        }
        return ans;
    }
//
//        public List<String> getDomainVisitCnt(String[] cpdomains) {
//        }

    public int getMaxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int max = 0; // 没有股票的收益
        int now = -prices[0]; // 买入股票的收益
        for (int i = 1; i < prices.length; i++) {
            int temp = max;
            max = Math.max(now + prices[i] - fee, max); // 买入 或者不买入
            now = Math.max(now, temp - prices[i]); //  持有不动 或者重新买入
        }
        return max;
    }



}
