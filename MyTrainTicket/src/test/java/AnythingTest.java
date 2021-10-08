
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
     * 查询一列车
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("train_no", "630000K2100I");
        urlBuilder.addQueryParameter("from_station_telecode", "YNG");
        urlBuilder.addQueryParameter("to_station_telecode", "NGH");
        urlBuilder.addQueryParameter("depart_date", "2021-10-06");

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
     * 查询余票
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        val url = "https://kyfw.12306.cn/otn/leftTicketPrice/query?leftTicketDTO.train_date=2021-10-09&leftTicketDTO.from_station=YGG&leftTicketDTO.to_station=NGH&leftTicketDTO.ticket_type=1&randCode=";
        val request = new Request.Builder().url(url).build();
        val response = getClient().newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void test4() throws IOException {
        val url = "https://kyfw.12306.cn/otn/leftTicket/queryY?leftTicketDTO.train_date=2021-10-07&leftTicketDTO.from_station=YNG&leftTicketDTO.to_station=NGH&purpose_codes=ADULT";
        val request = new Request.Builder().url(url).build();
        val response = getClient().newCall(request).execute();
        System.out.println(response.body().string());
    }

}
