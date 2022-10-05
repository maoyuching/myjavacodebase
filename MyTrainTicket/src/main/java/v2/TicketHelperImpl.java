package v2;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.val;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import v1.The12306Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TicketHelperImpl implements TicketHelper {

    @Override
    @SneakyThrows
    public Map<Seat, Integer> query(String from, String to, String train, LocalDate date) {

        // 1 query api get ticket list
        String url = UrlBuilder.of("https://kyfw.12306.cn/otn/leftTicket/queryZ")
                .addQuery("leftTicketDTO.train_date", date.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .addQuery("leftTicketDTO.from_station", The12306Util.getStationCodeByName(from))
                .addQuery("leftTicketDTO.to_station", The12306Util.getStationCodeByName(to))
                .addQuery("purpose_codes", "ADULT")
                .build();

        Request req = new Request.Builder()
                .url(url)
                .addHeader("Host", "kyfw.12306.cn")
                .addHeader("Cookie", "_uab_collina=166478337414337849621795; BIGipServerotn=149946890.50210.0000; RAIL_EXPIRATION=1665168151759; RAIL_DEVICEID=M70Vk7RCNg-z2fvIfYwHosQnEPxAn8EhcGS-AaKboIU7NyjjpDtpw1g8szJIVNcqKmzCAOaA2L6IPpOkejwt2nlgWJeLjo313kR8trG6vPVcNS3XYVOqadNtWVBnPvK5EbTxJna2xFnwvHJWtdBlViwT2ErJXJiI; _jc_save_fromStation=%u7389%u5C71%u5357%2CYGG; _jc_save_toStation=%u5B81%u6CE2%2CNGH; _jc_save_fromDate=2022-10-05; _jc_save_toDate=2022-10-05; CLIENT=wxgzh; JSESSIONID=")
                .build();

        Response rsp = new OkHttpClient.Builder().build().newCall(req).execute();

        // 2 filter the train we need , get yp_info field , find left ticket
        JSONObject jsonBody = JSONObject.parseObject(Objects.requireNonNull(rsp.body()).string());
        List<String> res = jsonBody.getJSONObject("data").getJSONArray("result").toJavaList(String.class);
        List<String[]> trainData = res.stream().map(s -> s.split("\\|")).collect(Collectors.toList());

        Optional<String[]> opt = trainData.stream().reduce((a, b) -> StrUtil.equals(a[3], train) ? a : b);
        List<String> data = Lists.newArrayList();
        if (opt.isPresent()) {
            data = Arrays.asList(opt.get());
        } else {
            return Maps.newHashMap();
        }

        Map<Seat, Integer> ans = Maps.newEnumMap(Seat.class);
        // 二等座
        val n = data.get(30);
        if (StringUtils.isNumeric(n)) {
            ans.put(Seat.EDZ, Integer.parseInt(n));
        } else if (StrUtil.equals(n, "有")) {
            ans.put(Seat.EDZ, 5);
        } else {
            ans.put(Seat.EDZ, 0);
        }
        System.out.println(from + to + ans);
        return ans;
    }

    @Override
    @SneakyThrows
    public List<SeatInfo> queryList(String from, String to, String train, LocalDate date) {

        // 1. find available station between the two
        String url = UrlBuilder.of("https://kyfw.12306.cn/otn/queryTrainInfo/query")
                .addQuery("leftTicketDTO.train_no", The12306Util.getTrainNoByTrainCode2(from, to, train, date))
                .addQuery("leftTicketDTO.train_date", date.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .addQuery("rand_code", "")
                .build();

        Request req = new Request.Builder()
                .url(url)
                .addHeader("Cookie", "JSESSIONID=9B6ED7D864A60D009A6E0D4E8E345460; BIGipServerotn=149946890.50210.0000; RAIL_EXPIRATION=1665168151759; RAIL_DEVICEID=M70Vk7RCNg-z2fvIfYwHosQnEPxAn8EhcGS-AaKboIU7NyjjpDtpw1g8szJIVNcqKmzCAOaA2L6IPpOkejwt2nlgWJeLjo313kR8trG6vPVcNS3XYVOqadNtWVBnPvK5EbTxJna2xFnwvHJWtdBlViwT2ErJXJiI; _jc_save_fromStation=%u7389%u5C71%u5357%2CYGG; _jc_save_toStation=%u5B81%u6CE2%2CNGH; _jc_save_fromDate=2022-10-05; _jc_save_toDate=2022-10-05; CLIENT=wxgzh; JSESSIONID=; _jc_save_fromStation=%u7389%u5C71%u5357%2CYGG; _jc_save_toStation=%u676D%u5DDE%2CHZH; _jc_save_toDate=2022-10-03; _jc_save_wfdc_flag=dc; _jc_save_fromDate=2022-10-05; route=6f50b51faa11b987e576cdb301e545c4")
                .addHeader("Host", "kyfw.12306.cn")
                .build();

        Response rsp = new OkHttpClient.Builder().build().newCall(req).execute();

        JSONObject body = JSONObject.parseObject(rsp.body().string());
        List<JSONObject> stationList = body.getJSONObject("data").getJSONArray("data").toJavaList(JSONObject.class);

        int i = ListUtils.indexOf(stationList, o -> o.getString("station_name").contains(from));
        int j = ListUtils.indexOf(stationList, o -> o.getString("station_name").contains(to));

        // 2. find all seat info
        List<SeatInfo> ans = Lists.newArrayList();
        for (int i1 = i + 1; i1 <= j; i1++) {
            SeatInfo info = new SeatInfo();
            info.setFrom(from);
            info.setTo(stationList.get(i1).getString("station_name"));
            info.setTrainCode(train);
            info.setData(query(from, stationList.get(i1).getString("station_name"), train, date));
            ans.add(info);
        }
        return ans;
    }
}
