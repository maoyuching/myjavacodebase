package v2;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TicketHelper {

    /**
     * 查询两个站之间 的余票
     * @param from 出发站
     * @param to arrive station
     * @param train train
     * @param date date
     * @return 座位 数量
     */
    Map<Seat, Integer> query(String from, String to, String train, LocalDate date);

    /**
     * 查询 所有可能的 买票方案
     * @param from
     * @param to
     * @param date
     */
    List<SeatInfo> queryList(String from, String to, String train, LocalDate date);

}
