package com.tojaoomy.demo.infra.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author 玉书
 * @date 2021/12/23
 */
public class ShardingUserTableAlgorithm implements PreciseShardingAlgorithm<String> {

    public static final String DEFAULT_DATABASE_NAME = "t_sharding_user_0";
    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<String> shardingValue) {
        String userId = shardingValue.getValue();
        if(StringUtils.isNotBlank(userId) && userId.length() >= 4) {
           return "t_sharding_user_" + NumberUtils.toInt(String.valueOf(userId.charAt(2)),0) % 2;
        }
        return DEFAULT_DATABASE_NAME;
    }
}