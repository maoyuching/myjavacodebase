import org.junit.Test;

import static org.junit.Assert.*;

public class HashsTest {

    @Test
    public void longestConsecutive() {
        int i = new Hashs().longestConsecutive(new int[]{100, 2, 1, 3, 4});
        assertEquals(i, 4);
    }
}