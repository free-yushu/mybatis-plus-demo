package com.tojaoomy.demo.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tojaoomy.demo.infra.dataobject.ShardingUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 玉书
 * @since 2021-12-25
 */
@Mapper
public interface ShardingUserMapper extends BaseMapper<ShardingUserDO> {

}
