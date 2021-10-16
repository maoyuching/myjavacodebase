package crawl;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 表示两个站点之间的 余票信息
 */
@Data
@Accessors(chain = true)
@Builder
public class LeftTicket {

    /** the station chinese name*/
    String fromStationName;
    String toStationName;

    /** 车等级 */
    TrainType trainType;
    /** 车等级 */
    public enum TrainType{
        KTrain, GTrain;
    }

    /** 二等座*/
    int seatSecond;
    /** 一等座*/
    int seatFirst;
    /** without seat ! */
    int seatWz;

    /** 银座软座*/
    int seatYz;
    int seatRz;

    /** 阴我软卧*/
    int bedYw;
    int bedRw;

}
