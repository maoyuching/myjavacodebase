package crawl;

import java.time.LocalDate;
import java.util.List;

/**
 * the checker
 */
public interface MyTicketChecker {

    /**
     * 查询 某一天的某一趟车的两站点之间的余票
     * @param trainCode like G123
     * @param from 车站代码 like YGG
     * @param to as from
     * @param date 日期
     * @return 余票数据
     */
    MyTicket check(String trainCode, String from, String to, LocalDate date);

    /**
     * 查询一趟车 某一天的两站点之间的所有站点
     * @param trainCode like G123
     * @param from 车站代码 like YGG
     * @param to as from
     * @param date 日期， 字符串格式 like 2021-01-01
     * @return list of station code, such as VAP
     */
    List<String> search(String trainCode, String from, String to, LocalDate date);
}
