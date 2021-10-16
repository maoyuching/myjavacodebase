package crawl;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.TrainUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class LeftTicketCheckerImpl implements LeftTicketChecker {
//    String url = "https://kyfw.12306.cn/otn/leftTicketPrice/query?leftTicketDTO.train_date=2021-10-09&leftTicketDTO.from_station=YGG&leftTicketDTO.to_station=NGH&leftTicketDTO.ticket_type=1&randCode=";
    String url = "https://kyfw.12306.cn/otn/leftTicketPrice/query";
    String dateParam = "leftTicketDTO.train_date";
    String fromParam = "leftTicketDTO.from_station";
    String toParam = "leftTicketDTO.to_station";
    String typeParam = "leftTicketDTO.ticket_type";
    String randParam = "randCode";

    @Override
    public LeftTicket check(String trainCode, String from, String to, LocalDate date) {
        String queryUrl = UrlBuilder.of(url)
                .addQuery(dateParam, date.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .addQuery(fromParam, from)
                .addQuery(toParam, to)
                .addQuery(typeParam, "1")
                .addQuery(randParam, "")
                .build();
        String body = HttpUtil.createGet(queryUrl).execute().body();

        JSONObject res = JSON.parseObject(body)
                .getJSONArray("data")
                .stream()
                .map(o -> (JSONObject) o)
                .map(o -> o.getJSONObject("queryLeftNewDTO"))
                .filter(o -> StrUtil.equals(trainCode,o.getString("station_train_code")))
                .findFirst()
                .orElseGet(null);

        // trans json obj to leftTicket
        return LeftTicketTranser.INSTANCE.fromJsonObject(res);
    }

    @Override
    public List<String> search(String trainCode, String from, String to, LocalDate date) {
        String url = UrlBuilder.of("https://kyfw.12306.cn/otn/czxx/queryByTrainNo")
                .addQuery("train_no", TrainUtil.getTrainNoByTrainCode(trainCode))
                .addQuery("from_station_telecode", from)
                .addQuery("to_station_telecode", to)
                .addQuery("depart_date", date.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();

        String body = HttpUtil.createGet(url).execute().body();

        JSONObject json = JSONObject.parseObject(body);

        List<String> res = json.getJSONObject("data").getJSONArray("data")
                .stream()
                .map(o -> (JSONObject) o)
                .map(o -> o.getString("station_name"))
                .map(TrainUtil::getStationCodeByName)
                .collect(Collectors.toList());
       return res.subList(res.indexOf(from), res.indexOf(to) + 1);
    }
}
