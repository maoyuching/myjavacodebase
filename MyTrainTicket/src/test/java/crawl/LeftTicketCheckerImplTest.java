package crawl;

import junit.framework.TestCase;

import java.time.LocalDate;

public class LeftTicketCheckerImplTest extends TestCase {

    public void testCheck() {
        MyTicketChecker checker = new LeftTicketCheckerImpl();

        checker.check("G1416", "NGH", "YGG", LocalDate.now().plusDays(1));
    }
}