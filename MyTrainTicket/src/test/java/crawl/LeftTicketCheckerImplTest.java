package crawl;

import junit.framework.TestCase;
import lombok.val;

public class LeftTicketCheckerImplTest extends TestCase {

    public void testCheck() {
        LeftTicketCheckerImpl checker = new LeftTicketCheckerImpl();
        val res = checker.check("K210", "YGG", "NGH", "2021-10-12");
        System.out.println(res);
    }
}