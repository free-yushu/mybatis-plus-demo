package com.tojaoomy.demo.infra.mapper;

import com.tojaoomy.demo.infra.dataobject.OrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author 玉书
 * @since 2021-12-25
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

}
