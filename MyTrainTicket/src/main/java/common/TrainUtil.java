package common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.val;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 一些 方法
 */
public class TrainUtil {

    private static final String nullStr = StrUtil.EMPTY;

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
        val request = new Request.Builder().url(qUrl).build();
        val response = new OkHttpClient().newCall(request).execute();
        val jsonO =  JSONObject.parseObject(response.body().string());

        return jsonO.getJSONArray("data").getJSONObject(0).getString("train_no");
    }
}
