package com.tojaoomy.demo.domain.order.repository;

import com.tojaoomy.demo.domain.order.OrderEntity;

/**
 * 适配领域模型对象到持久化对象
 * @author 玉书
 * @date 2021/12/25
 */
public interface IOrderRepository {

    public boolean exists(OrderEntity order);

    public void save(OrderEntity orderEntity);
}