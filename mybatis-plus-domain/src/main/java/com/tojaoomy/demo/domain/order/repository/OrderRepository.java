package com.tojaoomy.demo.domain.order.repository;

import com.tojaoomy.demo.domain.order.OrderAggregation;
import com.tojaoomy.demo.domain.order.mapping.OrderMapping;
import com.tojaoomy.demo.infra.mapper.OrderMapper;
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
    public void save(OrderAggregation orderAggregation) {
        orderMapper.insert(orderMapping.toDataObject(orderAggregation));
    }
}