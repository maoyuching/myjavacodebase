import lombok.Data;

import java.time.LocalDate;
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
    @Override
    public List<?> getTrainTickets() {
        return null;
    }
}
