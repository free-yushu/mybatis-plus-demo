package com.tojaoomy.test;

import com.alibaba.fastjson.JSON;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.tojaoomy.demo.infra.dataobject.OrderDO;
import org.junit.jupiter.api.Test;

/**
 * @author 玉书
 * @date 2021/12/25
 */
public class JMockTest {

    private static MockConfig mockConfig = new MockConfig().globalConfig();

    @Test
    public void test() {
        System.out.println(JSON.toJSONString(JMockData.mock(OrderDO.class)));

    }

}