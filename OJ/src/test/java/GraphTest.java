import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void calcEquation() {
        List<List<String>> eq = new ArrayList<>();
        eq.add(Arrays.asList("a", "b"));
        eq.add(Arrays.asList("b", "c"));
        eq.add(Arrays.asList("c", "d"));
        double[] v = new double[3];
        v[0] = 2.0;
        v[1] = 3.0;
        v[2] = 4.0;

        List<List<String>> q = new ArrayList<>();
        q.add(Arrays.asList("a", "c"));
        q.add(Arrays.asList("b", "a"));
        q.add(Arrays.asList("a", "e"));
        q.add(Arrays.asList("a", "a"));
        q.add(Arrays.asList("x", "x"));
        q.add(Arrays.asList("a", "d"));
        double[] ans = new Graph().calcEquation(eq, v, q);
        for (double an : ans) {
            System.out.println(an);
        }
    }
}