import huawei.Huawei;
import org.junit.Test;

import static org.junit.Assert.*;

public class HuaweiTest {

    @Test
    public void main() {
    }

    @Test
    public void compator() {
        assertTrue(Huawei.compator("01:41:8.9", "1:1:09.211")> 0);
    }

    @Test
    public void trim1() {
        assertEquals("1", Huawei.trim1("01"));
        assertEquals("1", Huawei.trim1("1"));
        assertEquals("0", Huawei.trim1("0"));
    }
}