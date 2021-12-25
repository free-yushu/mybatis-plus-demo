package com.tojaoomy.demo.domain.order.factory;

import com.tojaoomy.demo.api.order.dto.CreateOrderRequest;
import com.tojaoomy.demo.domain.order.OrderAggregation;
import com.tojaoomy.demo.domain.order.repository.IOrderRepository;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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

    public OrderAggregation createOrder(CreateOrderRequest request) {
        return OrderAggregation.builder()
                .orderId(request.getOrderId())
                .orderRepository(orderRepository)
                .build();
    }
}