package com.tojaoomy.demo.infra.config;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 基于Hint的分表策略, 适用于仅从SQL无法断定分表的场景.
 * 注意: 如未明确使用HintManager指定分表, 则默认会遍历所有分表
 *
 * @author hejian
 */
@Slf4j
public class TableHintShardingAlgorithm implements HintShardingAlgorithm<String> {

    /**
     * @param availableTargetNames 目标数据源名称或数据表名称, 需要注意的是这里为逻辑数据源和逻辑表名, 非实际表名
     * @param shardingValue        分片值, 来自SQL中分片字段对应的值
     * @return 真实的数据源名称或数据表名称
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
        if (CollectionUtils.isEmpty(shardingValue.getValues())) {
            log.error("can not hint table, reason: no hint value");
            return availableTargetNames;
        }

        String tag = String.valueOf(new ArrayList<>(shardingValue.getValues()).get(0));
        for (String availableTargetName : availableTargetNames) {
            if (availableTargetName.endsWith(tag)) {
                return ImmutableList.of(availableTargetName);
            }
        }

        log.error("can not hint table, reason: hint fail, hint value: {}", tag);
        return availableTargetNames;
    }
}
