package com.tojaoomy.test;

import com.alibaba.fastjson.JSON;
import com.github.jsonzou.jmockdata.MockConfig;
import com.tojaoomy.demo.infra.dataobject.OrderDO;
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
@ActiveProfiles("test")
@Sql(scripts = "/h2_sql/delete.sql")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestDataSourceConfig.class, TestMybatisPlusConfiguration.class })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class }, scanBasePackages = {"com.tojaoomy.demo"})
public class ProfileTestApplicationTest {

    private static MockConfig mockConfig = new MockConfig()
            // 全局配置
            .globalConfig();

    @Test
    @Sql(statements = "insert into t_order_complaint_flow_mapping (id, deleted, flow_id, flow_detail_id, is_active_flow, flow_status) values  (10, 0, 0, 0, 0, 'init');")
    public void testSqlInsert() {

    }

    /**
     * 测试乐观锁
     */
    @Test
    public void testVersion() {
        OrderDO orderDO = new OrderDO();
        orderDO.selectById(1).setOrderId("1234").updateById();
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