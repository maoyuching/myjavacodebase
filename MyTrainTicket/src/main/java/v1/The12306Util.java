package v1;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
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
import org.apache.commons.lang3.StringUtils;
import v2.TicketHelper;

import java.security.interfaces.RSAPrivateCrtKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 一些 方法
 * 12306 接口 facade
 */
public class The12306Util {

    private static final String nullStr = StrUtil.EMPTY;

    static OkHttpClient client = new OkHttpClient();

    /**
     * 根据汉语名字获取 车站英文代号
     * @param stationName 北京北
     * @return VAP
     */
    @SneakyThrows
    public static String getStationCodeByName(String stationName) {
        // url 就这些，也不需要拼接了
        String url = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9047";
        Request request = new Request.Builder().url(url).build();
        Response response = new OkHttpClient().newCall(request).execute();
        String body = response.body().string();

        List<String> stations = new ArrayList<>();
        CollUtil.addAll(stations, body.split("\\|"));

        Assert.isTrue(stations.contains(stationName),"没有这个站名 {}",stationName);
        int i = stations.indexOf(stationName);
        if (i >= stations.size()) {
            return nullStr;
        }
        return stations.get(i + 1);
    }

    /**
     * 根据火车 code 获取 编号
     * @param code like G1416
     * @return like "5e000G141672"
     */
    @SneakyThrows
    public static String getTrainNoByTrainCode(String code) {
        val dateArg = DateUtil.format(LocalDateTime.now(), "yyyyMMdd");
        val qUrl = UrlBuilder.of("https://search.12306.cn/search/v1/train/search")
                .addQuery("keyword", code)
                .addQuery("date", dateArg)
                .build();
        val request = new Request.Builder()
                .url(qUrl)
                .addHeader("Host", "search.12306.cn")
//                .addHeader("Origin", "https://kyfw.12306.cn")
//                .addHeader("Referer", "https://kyfw.12306.cn/")
                .addHeader("Cookie", "BIGipServerpool_restapi=2262893066.44582.0000; RAIL_EXPIRATION=1665168151759; RAIL_DEVICEID=M70Vk7RCNg-z2fvIfYwHosQnEPxAn8EhcGS-AaKboIU7NyjjpDtpw1g8szJIVNcqKmzCAOaA2L6IPpOkejwt2nlgWJeLjo313kR8trG6vPVcNS3XYVOqadNtWVBnPvK5EbTxJna2xFnwvHJWtdBlViwT2ErJXJiI; _jc_save_fromStation=%u7389%u5C71%u5357%2CYGG; _jc_save_toStation=%u5B81%u6CE2%2CNGH; _jc_save_fromDate=2022-10-05; _jc_save_toDate=2022-10-05; CLIENT=wxgzh; JSESSIONID=")
                .build();
        val response = new OkHttpClient().newCall(request).execute();

        System.out.println(request);
        System.out.println(response);
        val jsonO =  JSONObject.parseObject(response.body().string());

        return jsonO.getJSONArray("data").getJSONObject(0).getString("train_no");
    }

    /**
     * 根据火车 code 获取 编号
     * @param code like G1416
     * @return like "5e000G141672"
     */
    @SneakyThrows
    public static String getTrainNoByTrainCode2(String from , String to, String train , LocalDate date) {

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

        Optional<String[]> opt = trainData.stream().reduce((a, b) -> StrUtil.contains(a[3], train) ? a : b);
        List<String> data = Lists.newArrayList();
        if (opt.isPresent()) {
            return opt.get()[2];
        } else {
            return "";
        }
    }
}
