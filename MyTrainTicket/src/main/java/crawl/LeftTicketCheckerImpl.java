package crawl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.val;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class LeftTicketCheckerImpl implements LeftTicketChecker {
//    String url = "https://kyfw.12306.cn/otn/leftTicketPrice/query?leftTicketDTO.train_date=2021-10-09&leftTicketDTO.from_station=YGG&leftTicketDTO.to_station=NGH&leftTicketDTO.ticket_type=1&randCode=";
    String url = "https://kyfw.12306.cn/otn/leftTicketPrice/query";
    String dateParam = "leftTicketDTO.train_date";
    String fromParam = "leftTicketDTO.from_station";
    String toParam = "leftTicketDTO.to_station";
    String typeParam = "leftTicketDTO.ticket_type";
    String randParam = "randCode";

    @SneakyThrows
    @Override
    public LeftSeat check(String trainCode, String from, String to, String date) {
        val url = "https://kyfw.12306.cn/otn/leftTicketPrice/query?leftTicketDTO.train_date=2021-10-09&leftTicketDTO.from_station=YGG&leftTicketDTO.to_station=NGH&leftTicketDTO.ticket_type=1&randCode=";

        val ub = HttpUrl.parse(this.url).newBuilder();
        ub.addQueryParameter(this.dateParam, date);
        ub.addQueryParameter(this.fromParam, from);
        ub.addQueryParameter(this.toParam, to);
        ub.addQueryParameter(this.typeParam, "1");
        ub.addQueryParameter(this.randParam, "");
        val request = new Request.Builder().url(ub.build().toString()).build();
        val response = new OkHttpClient().newCall(request).execute();

        String body = response.body().string();
        JSONObject left = JSON.parseObject(body)
                .getJSONArray("data")
                .stream()
                .map(o -> (JSONObject) o)
                .map(o -> o.getJSONObject("queryLeftNewDTO"))
                .filter(o -> trainCode.equals(o.getString("station_train_code")))
                .findFirst()
                .orElseGet(null);
        LeftSeat  leftSeat = left.toJavaObject(LeftSeat.class);

        System.out.println(response.body().string());
        return leftSeat;
    }
}
