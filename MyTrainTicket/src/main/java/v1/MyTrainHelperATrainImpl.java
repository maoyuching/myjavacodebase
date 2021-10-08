package v1;

import lombok.Data;
import lombok.val;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 一个车次的  车票 购买方案
 */
@Data
public class MyTrainHelperATrainImpl implements MyTrainHelper {

    /**
     *  like G1234
     */
    String train;

    /**
     * 当日车次
     */
    LocalDate date;

    /**
     *  你想要从哪里出发
     */
    Station from;

    Station to;

    @Override
    public List<? extends Ticket> getTrainTickets() {
        val tk = new Ticket();
        return Arrays.asList(tk);
    }
}
