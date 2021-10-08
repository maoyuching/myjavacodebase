package crawl;

import com.alibaba.fastjson.JSONObject;

public interface LeftTicketChecker {

    /**
     * 查询 两地之间的票的情况
     * @param trainCode
     * @param from
     * @param to
     * @param date
     * @return
     */
    LeftSeat check(String trainCode, String from, String to, String date);
}
