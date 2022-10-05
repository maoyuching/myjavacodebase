package v2;

import lombok.Data;

import java.util.Map;

/**
 * 余票信息
 */
@Data
public
class SeatInfo {
    String from;
    String to;
    String trainCode;
    // 座位 信息
    Map<Seat, Integer> data;
}
