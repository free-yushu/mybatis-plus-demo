package com.tojaoomy.demo.domain.order.factory;

import com.tojaoomy.demo.api.order.dto.CreateOrderRequest;
import com.tojaoomy.demo.domain.order.OrderEntity;
import com.tojaoomy.demo.domain.order.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 玉书
 * @date 2021/12/25
 */
@Component
public class OrderFactory {

    @Autowired
    private IOrderRepository orderRepository;

    public OrderEntity createOrder(CreateOrderRequest request) {
        return OrderEntity.builder()
                .orderId(request.getOrderId())
                .orderRepository(orderRepository)
                .orderRepository2(orderRepository)
                .build();
    }
}