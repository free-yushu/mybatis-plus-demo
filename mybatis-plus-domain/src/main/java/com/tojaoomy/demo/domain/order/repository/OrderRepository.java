package com.tojaoomy.demo.domain.order.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.base.Objects;
import com.tojaoomy.demo.domain.order.OrderEntity;
import com.tojaoomy.demo.domain.order.mapping.OrderMapping;
import com.tojaoomy.demo.infra.dataobject.OrderDO;
import com.tojaoomy.demo.infra.mapper.OrderMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 玉书
 * @date 2021/12/25
 */
@Service
public class OrderRepository implements IOrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderMapping orderMapping;

    public static OrderRepository of() {
        return new OrderRepository();
    }

    @Override
    public boolean exists(OrderEntity entity) {
        LambdaQueryWrapper<OrderDO> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ObjectUtils.allNotNull(entity, entity.getOrderId()), OrderDO::getOrderId, entity.getOrderId());
        return orderMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public void save(OrderEntity orderEntity) {
        orderMapper.insert(orderMapping.toDataObject(orderEntity));
    }
}