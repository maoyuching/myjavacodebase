
import lombok.val;
import okhttp3.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AnythingTest {

    String BASE_URL = "https://kyfw.12306.cn/otn/czxx/queryByTrainNo";

    public OkHttpClient getClient () {
        return new OkHttpClient();
    }

    public Response queryATrain(String trainNo,String from, String to, String date) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter("train_no", "630000K2100I");
        urlBuilder.addQueryParameter("station_train_code", trainNo);
        urlBuilder.addQueryParameter("from_station_telecode", from);
        urlBuilder.addQueryParameter("to_station_telecode", to);
        urlBuilder.addQueryParameter("depart_date", date);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);

        return call.execute();
    }

    /**
     * 查询一列车 经停的 车站 列表
     *
     * {
     *   "validateMessagesShowId": "_validatorMessage",
     *   "status": true,
     *   "httpstatus": 200,
     *   "data": {
     *     "data": [
     *       {
     *         "start_station_name": "广州",
     *         "arrive_time": "00:00",
     *         "station_train_code": "K210",
     *         "station_name": "广州",
     *         "train_class_name": "快速",
     *         "service_type": "1",
     *         "start_time": "20:55",
     *         "stopover_time": "----",
     *         "end_station_name": "宁波",
     *         "station_no": "01",
     *         "isEnabled": false
     *       },
     *       {
     *         "arrive_time": "16:43",
     *         "station_name": "宁波",
     *         "start_time": "16:43",
     *         "stopover_time": "----",
     *         "station_no": "18",
     *         "isEnabled": true
     *       }
     *     ]
     *   },
     *   "messages": [],
     *   "validateMessages": {}
     * }
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://kyfw.12306.cn/otn/czxx/queryByTrainNo").newBuilder();
        urlBuilder.addQueryParameter("train_no", "630000K2100I");
        urlBuilder.addQueryParameter("from_station_telecode", "YNG");
        urlBuilder.addQueryParameter("to_station_telecode", "NGH");
        urlBuilder.addQueryParameter("depart_date", "2021-10-12");

        String url = urlBuilder.build().toString();

        System.out.println(url);
        Request request = new Request.Builder().url(url).build();

        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);
        Response response = call.execute();

        Assert.assertEquals(response.code(), 200);

        System.out.println(response.body().string());
    }

    /**
     * 查询 所有 车站 和 代码， JavaScript对象格式
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        String url = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9047";
        Request request = new Request.Builder().url(url).build();

        Response response = getClient().newCall(request).execute();

        System.out.println(response.body().string());

    }

    /**
     * 查询两地间火车余票, 包含多趟车
     *
     *  {
     *   "validateMessagesShowId": "_validatorMessage",
     *   "status": true,
     *   "httpstatus": 200,
     *   "data": [
     *     {
     *       "queryLeftNewDTO": {
     *         "train_no": "630000K2100I",
     *         "station_train_code": "K210",
     *         "start_station_telecode": "GZQ",
     *         "start_station_name": "广州",
     *         "end_station_telecode": "NGH",
     *         "end_station_name": "宁波",
     *         "from_station_telecode": "YNG",
     *         "from_station_name": "玉山",
     *         "to_station_telecode": "NGH",
     *         "to_station_name": "宁波",
     *         "start_time": "09:17",
     *         "arrive_time": "16:43",
     *         "day_difference": "0",
     *         "train_class_name": "",
     *         "lishi": "07:26",
     *         "is_support_card": "1",
     *         "control_train_day": "20991231",
     *         "start_train_date": "20211006",
     *         "seat_feature": "334313W3",
     *         "yp_ex": "304010W0",
     *         "train_seat_feature": "3",
     *         "controlled_train_flag": "0",
     *         "controlled_train_message": "正常车次，不受控",
     *         "rw_num": "有",
     *         "srrb_num": "-1",
     *         "gg_num": "-1",
     *         "gr_num": "-1",
     *         "rz_num": "-1",
     *         "tz_num": "-1",
     *         "wz_num": "有",
     *         "yb_num": "-1",
     *         "yw_num": "有",
     *         "yz_num": "有",
     *         "ze_num": "-1",
     *         "zy_num": "-1",
     *         "swz_num": "-1",
     *         "wz_seat_type_code": "1",
     *         "yz_price": "00750",
     *         "rz_price": "--",
     *         "yw_price": "01330",
     *         "rw_price": "02050",
     *         "gr_price": "--",
     *         "zy_price": "--",
     *         "ze_price": "--",
     *         "tz_price": "--",
     *         "gg_price": "--",
     *         "yb_price": "--",
     *         "bxyw_num": "-1",
     *         "bxyw_price": "--",
     *         "swz_price": "--",
     *         "hbyz_num": "-1",
     *         "hbyz_price": "--",
     *         "hbyw_num": "-1",
     *         "hbyw_price": "--",
     *         "bxrz_num": "-1",
     *         "bxrz_price": "--",
     *         "tdrz_num": "-1",
     *         "tdrz_price": "--",
     *         "srrb_price": "--",
     *         "errb_num": "-1",
     *         "errb_price": "--",
     *         "yrrb_num": "-1",
     *         "yrrb_price": "--",
     *         "ydsr_num": "-1",
     *         "ydsr_price": "--",
     *         "edsr_num": "-1",
     *         "edsr_price": "--",
     *         "hbrz_num": "-1",
     *         "hbrz_price": "--",
     *         "hbrw_num": "-1",
     *         "hbrw_price": "--",
     *         "ydrz_num": "-1",
     *         "ydrz_price": "--",
     *         "edrz_num": "-1",
     *         "edrz_price": "--",
     *         "wz_price": "00750"
     *       },
     *       "buttonTextInfo": ""
     *     },
     *     {
     *       "queryLeftNewDTO": {
     *         "train_no": "6c000G143409",
     *         "station_train_code": "G1434",
     *         "start_station_telecode": "CWQ",
     *         "start_station_name": "长沙南",
     *         "end_station_telecode": "NGH",
     *         "end_station_name": "宁波",
     *         "from_station_telecode": "YGG",
     *         "from_station_name": "玉山南",
     *         "to_station_telecode": "NGH",
     *         "to_station_name": "宁波",
     *         "start_time": "13:01",
     *         "arrive_time": "15:46",
     *         "day_difference": "0",
     *         "train_class_name": "",
     *         "lishi": "02:45",
     *         "is_support_card": "1",
     *         "control_train_day": "20991231",
     *         "start_train_date": "20211007",
     *         "seat_feature": "O3M393",
     *         "yp_ex": "O0M090",
     *         "train_seat_feature": "3",
     *         "controlled_train_flag": "0",
     *         "controlled_train_message": "正常车次，不受控",
     *         "rw_num": "-1",
     *         "srrb_num": "-1",
     *         "gg_num": "-1",
     *         "gr_num": "-1",
     *         "rz_num": "-1",
     *         "tz_num": "-1",
     *         "wz_num": "-1",
     *         "yb_num": "-1",
     *         "yw_num": "-1",
     *         "yz_num": "-1",
     *         "ze_num": "有",
     *         "zy_num": "有",
     *         "swz_num": "有",
     *         "yz_price": "--",
     *         "rz_price": "--",
     *         "yw_price": "--",
     *         "rw_price": "--",
     *         "gr_price": "--",
     *         "zy_price": "03325",
     *         "ze_price": "01975",
     *         "tz_price": "--",
     *         "gg_price": "--",
     *         "yb_price": "--",
     *         "bxyw_num": "-1",
     *         "bxyw_price": "--",
     *         "swz_price": "06235",
     *         "hbyz_num": "-1",
     *         "hbyz_price": "--",
     *         "hbyw_num": "-1",
     *         "hbyw_price": "--",
     *         "bxrz_num": "-1",
     *         "bxrz_price": "--",
     *         "tdrz_num": "-1",
     *         "tdrz_price": "--",
     *         "srrb_price": "--",
     *         "errb_num": "-1",
     *         "errb_price": "--",
     *         "yrrb_num": "-1",
     *         "yrrb_price": "--",
     *         "ydsr_num": "-1",
     *         "ydsr_price": "--",
     *         "edsr_num": "-1",
     *         "edsr_price": "--",
     *         "hbrz_num": "-1",
     *         "hbrz_price": "--",
     *         "hbrw_num": "-1",
     *         "hbrw_price": "--",
     *         "ydrz_num": "-1",
     *         "ydrz_price": "--",
     *         "edrz_num": "-1",
     *         "edrz_price": "--",
     *         "wz_price": "--"
     *       },
     *       "buttonTextInfo": ""
     *     },
     *       "buttonTextInfo": ""
     *     }
     *   ],
     *   "messages": [],
     *   "validateMessages": {}
     * }
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        val url = "https://kyfw.12306.cn/otn/leftTicketPrice/query?leftTicketDTO.train_date=2021-10-12&leftTicketDTO.from_station=YGG&leftTicketDTO.to_station=NGH&leftTicketDTO.ticket_type=1&randCode=";
        val request = new Request.Builder().url(url).build();
        val response = getClient().newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void test4() throws IOException {
        val url = "https://kyfw.12306.cn/otn/leftTicket/queryY?leftTicketDTO.train_date=2021-10-18&leftTicketDTO.from_station=YNG&leftTicketDTO.to_station=NGH&purpose_codes=ADULT";
        val request = new Request.Builder().url(url).build();
        val response = getClient().newCall(request).execute();
        System.out.println(response.body().string());
    }

    /**
     * query train info, 查询一趟车, 经过的车站信息
     *
     * {
     *   "validateMessagesShowId": "_validatorMessage",
     *   "status": true,
     *   "httpstatus": 200,
     *   "data": {
     *     "data": [
     *       {
     *         "arrive_day_str": "当日到达",
     *         "station_name": "广州",
     *         "train_class_name": "快速",
     *         "is_start": "Y",
     *         "service_type": "1",
     *         "end_station_name": "宁波",
     *         "arrive_time": "----",
     *         "start_station_name": "广州",
     *         "station_train_code": "K210",
     *         "arrive_day_diff": "0",
     *         "start_time": "20:53",
     *         "station_no": "01",
     *         "wz_num": "--",
     *         "running_time": "00:00"
     *       },
     *       {
     *         "arrive_day_str": "当日到达",
     *         "arrive_time": "23:14",
     *         "station_train_code": "K210",
     *         "station_name": "韶关东",
     *         "arrive_day_diff": "0",
     *         "OT": [],
     *         "start_time": "23:20",
     *         "wz_num": "--",
     *         "station_no": "02",
     *         "running_time": "02:21"
     *       },
     *       {
     *         "arrive_day_str": "次日到达",
     *         "arrive_time": "16:43",
     *         "station_train_code": "K211",
     *         "station_name": "宁波",
     *         "arrive_day_diff": "1",
     *         "OT": [],
     *         "start_time": "16:43",
     *         "wz_num": "--",
     *         "station_no": "18",
     *         "running_time": "19:50"
     *       }
     *     ]
     *   },
     *   "messages": [],
     *   "validateMessages": {}
     * }
     * @throws IOException
     */
    @Test
    public void test5() throws IOException {
        val url = "https://kyfw.12306.cn/otn/queryTrainInfo/query?leftTicketDTO.train_no=630000K2100I&leftTicketDTO.train_date=2021-10-12&rand_code=";
        val request = new Request.Builder().url(url).build();
        val response = getClient().newCall(request).execute();
        System.out.println(response.body().string());
    }

    /**
     * 根据车次号G123，查询列车信息
         {
         "data": [
         {
         "date": "20211010",
         "from_station": "上海虹桥",
         "station_train_code": "G1375",
         "to_station": "昆明南",
         "total_num": "25",
         "train_no": "5l000G137585"
         }
         ],
         "status": true,
         "errorMsg": ""
         }
     * @throws IOException
     */
    @Test
    public void test6() throws IOException {
        val url = "https://search.12306.cn/search/v1/train/search?keyword=G1375&date=20211010";
        val request = new Request.Builder().url(url).build();
        val response = getClient().newCall(request).execute();
        System.out.println(response.body().string());
    }


}
