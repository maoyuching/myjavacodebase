package common;

import cn.hutool.core.util.StrUtil;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import v1.The12306Util;

import java.time.LocalDate;

public class TrainUtilTest {

    /**
     * 测试 根据 火车站中文名获取 英文代号
     */
    @Test
    public void getStationCodeByName() {
        val nb = The12306Util.getStationCodeByName("宁波");

        Assert.assertEquals(The12306Util.getStationCodeByName("宁波"), "NGH");
        val ysn = The12306Util.getStationCodeByName("玉山南");
        Assert.assertEquals(The12306Util.getStationCodeByName("玉山南"), "YGG");

    }

    @Test
    public void getTrainNoByTrainCode() {
        val trainNo = The12306Util.getTrainNoByTrainCode2("玉山南", "宁波", "G1416", LocalDate.now());
        System.out.println(trainNo);
        Assert.assertTrue(StrUtil.isNotEmpty(trainNo));
    }
}