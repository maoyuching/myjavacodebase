package common;

import cn.hutool.core.util.StrUtil;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

public class TrainUtilTest {

    /**
     * 测试 根据 火车站中文名获取 英文代号
     */
    @Test
    public void getStationCodeByName() {
        val nb = TrainUtil.getStationCodeByName("宁波");
        System.out.println(nb);
        Assert.assertEquals(TrainUtil.getStationCodeByName("宁波"), "NGH");
    }

    @Test
    public void getTrainNoByTrainCode() {
        val trainNo = TrainUtil.getTrainNoByTrainCode("G1416");
        System.out.println(trainNo);
        Assert.assertTrue(StrUtil.isNotEmpty(trainNo));
    }
}