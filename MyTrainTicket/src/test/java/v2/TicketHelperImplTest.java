package v2;

import lombok.val;
import org.junit.Test;

import java.time.LocalDate;

public class TicketHelperImplTest {

    @Test
    public void t1() {
        TicketHelperImpl ticketHelper = new TicketHelperImpl();
        ticketHelper.query("玉山南", "宁波", "G1434", LocalDate.now().plusDays(1));
    }

    @Test
    public void t2() {

        TicketHelperImpl ticketHelper = new TicketHelperImpl();
        val ans = ticketHelper.queryList("玉山南", "宁波", "G1434", LocalDate.now().plusDays(1));
        System.out.println(ans);
    }

    @Test
    public void t3() {

        TicketHelperImpl ticketHelper = new TicketHelperImpl();
        val ans = ticketHelper.queryList("玉山南", "杭州东", "G170", LocalDate.now().plusDays(1));
        System.out.println(ans);
    }
}