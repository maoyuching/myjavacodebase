import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;


public class ArrayTest {

    @Test
    public void reconstructQueue() {
        int[][] people = new int[2][6];

        people = new int[][]{{4, 4}, {5, 0}, {5, 2}, {7, 0}, {7, 1}, {6, 1}};

        int[][] ans =  new Array().reconstructQueue(people);
        System.out.println(ans);
    }

    @Test
    public void canPartition() {

        Function<int[], Boolean> f = (a) ->{
            boolean ans = new Array().canPartition(a);
            System.out.println(ans);
            return ans;
        };

        boolean b1 = f.apply(new int[]{1, 5, 11, 5});
        boolean b2 = f.apply(new int[]{2, 2, 1, 1});
        boolean b3 = f.apply(new int[]{1, 1, 1, 1});
//        boolean b4 = f.apply(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97});
        boolean b5 = f.apply(new int[]{2, 2, 3, 5});

        Assert.assertTrue(b1);
        Assert.assertTrue(b2);
        Assert.assertTrue(b3);
//        Assert.assertTrue(b4);
        Assert.assertTrue(b5);
    }

}