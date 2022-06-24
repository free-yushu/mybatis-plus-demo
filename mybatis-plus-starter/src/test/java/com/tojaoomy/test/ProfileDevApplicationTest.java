package com.tojaoomy.test;

import com.alibaba.fastjson.JSON;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.xiaoymin.knife4j.annotations.Ignore;
import com.tojaoomy.demo.infra.dataobject.OrderDO;
import com.tojaoomy.demo.infra.dataobject.ShardingUserDO;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author 玉书
 * @date 2021/1/26
 */
@SpringBootTest
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestDataSourceConfig.class, TestMybatisPlusConfiguration.class })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class }, scanBasePackages = {"com.tojaoomy"})
public class ProfileDevApplicationTest {

    private static MockConfig mockConfig = new MockConfig()
            // 全局配置
            .globalConfig();

    @Test
    @Ignore
    public void accountExistsTest() {
    }

    /**
     * 测试自动执行SQL
     */
    @Test
    @Sql(statements = "INSERT INTO t_order ( ctime, mtime, deleted, order_id, version ) VALUES ( '2021-12-25 19:26:02', '2021-12-25 19:26:02', 34, 'zfN4h', 1259 );")
    public void testSqlInsertOrder() {

    }

    @Test
    public void testInsertOrder() {
        JMockData.mock(OrderDO.class).setId(null).insert();
    }

    @Test
    public void testSharding() {
        for (int i = 0; i < 100; i++) {
            ShardingUserDO shardingUserDO = new ShardingUserDO();
            shardingUserDO.setUserId("1" + RandomUtils.nextInt(0, 10) + RandomUtils.nextInt(0, 10) + i);
            shardingUserDO.insert();
        }
    }

    private static void printJSON(Object object) {
        System.out.println(JSON.toJSONString(object, true));
    }

    @BeforeEach
    public void after() {
        System.out.println("before !!!");
    }

    @AfterAll
    public static void afterClass() {
        System.out.println("after class");
    }

}