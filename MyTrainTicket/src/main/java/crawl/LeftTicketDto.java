package crawl;

import lombok.Data;

@Data
public class LeftTicketDto {

    String from_station_telecode;// GZQ
    String station_train_code; //

    String wz_num;
    String zy_num; // 1st seat
    String ze_num; // 2nd seat

    String rw_num;
    String yw_num;
    String yz_num;



}
