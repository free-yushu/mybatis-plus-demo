package com.tojaoomy.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 玉书
 * @date 2021/12/20
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
public class GeneratorDataSourceConfigFactory {

    public static GeneratorDataSourceConfig createShardingUser() {
        return GeneratorDataSourceConfig.builder()
                .dataSourceSchema("sharding_user_0")
                .dataSourceIp("127.0.0.1")
                .dataSourcePort(3306)
                .username("root")
                .password("123456")
                .tablePrefixes(new String[]{"t_"})
                .tableSuffixes(new String[]{"_0"})
                .build();
    }

    public static GeneratorDataSourceConfig createTradeOrder() {
        return GeneratorDataSourceConfig.builder()
                .dataSourceSchema("trade_order")
                .dataSourceIp("127.0.0.1")
                .dataSourcePort(3306)
                .username("root")
                .password("123456")
                .tablePrefixes(new String[]{"t_"})
                .tableSuffixes(new String[]{"_0"})
                .build();
    }

    public static GeneratorDataSourceConfig createSchool() {
        return GeneratorDataSourceConfig.builder()
                .dataSourceSchema("school")
                .dataSourceIp("127.0.0.1")
                .dataSourcePort(3306)
                .username("root")
                .password("123456")
                .tablePrefixes(new String[]{"t_"})
                .tableSuffixes(new String[]{"_0"})
                .build();
    }

    public static GeneratorDataSourceConfigFactory of() {
        return new GeneratorDataSourceConfigFactory();
    }
}