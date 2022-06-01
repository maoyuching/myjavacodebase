import lombok.val;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void test() {
        val sol = new Solution();
        int ans = sol.getMatchedManagers(new int[]{1, 2, 3}, new int[]{1, 1});
        assertEquals(1, ans);
    }

    @Test
    public void test2() {
        val sol = new Solution();
        int ans = sol.getMatchedManagers(new int[]{1, 2}, new int[]{1, 2,3});
        assertEquals(2, ans);
    }

    @Test
    public void test3() {
        val sol = new Solution();
        int ans = sol.getMatchedManagers(new int[]{2, 2}, new int[]{1, 2,3});
        assertEquals(2, ans);
    }
    @Test
    public void test4() {
        val sol = new Solution();
        int ans = sol.getMatchedManagers(new int[]{}, new int[]{1});
        assertEquals(0, ans);
    }

    @Test
    public void test5() {
        val sol = new Solution();
        int ans = sol.getMatchedManagers(new int[]{1,3}, new int[]{1});
        assertEquals(1, ans);
    }

    @Test
    public void test6() {
        val sol = new Solution();
        int ans = sol.getMaxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2);
        assertEquals(ans, 8);
    }

    @Test
    public void test7() {
        val sol = new Solution();
        int ans = sol.getMaxProfit(new int[]{8,1,3,7,5,10,3}, 3);
        assertEquals(ans, 6);
    }


    @Test
    public void test8() {
        val sol = new Solution();
        int ans = sol.getMaxProfit(new int[]{7,6,5,4,3}, 3);
        assertEquals(ans, 0);
    }

    @Test
    public void test9() {
        val sol = new Solution();
        int ans = sol.getMaxProfit(new int[]{1,2,3}, 3);
        assertEquals(ans, 0);
    }

}