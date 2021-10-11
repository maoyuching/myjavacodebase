package common;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

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
}