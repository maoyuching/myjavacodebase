package crawl;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 一张单程票
 * 表示两个站点之间的 余票信息
 */
@Data
@Accessors(chain = true)
@Builder
public class MyTicket {

    /** the station chinese name*/
    String fromStationName;
    String toStationName;

    /** like G1234 */
    String trainCode;

    /** 车等级, 貌似没什么软用 */
    TrainType trainType;
    /** 车等级 */
    public enum TrainType{
        KTrain(1), GTrain(2);
        int type;
        TrainType(int i) {
            this.type = i;
        }
    }

    /** 二等座*/
    int edz;
    /** 一等座*/
    int ydz;
    /** 无座 */
    int wz;

    /** 硬座软座*/
    int yz;
    int rz;

    /** 硬卧软卧*/
    int yw;
    int rw;

    /**
     *  根据 余票 dto json 文件生成对象
     * @param j leftDto
     * @return the obj
     */
    public static MyTicket valueOf(JSONObject j) {
        return null;
    }
}
