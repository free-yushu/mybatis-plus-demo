package com.tojaoomy.demo.application.order.service;

import com.tojaoomy.demo.api.order.dto.CreateOrderRequest;
import com.tojaoomy.demo.api.order.dto.CreateOrderResponse;
import com.tojaoomy.demo.api.support.ResultEntity;
import com.tojaoomy.demo.domain.order.OrderEntity;
import com.tojaoomy.demo.domain.order.factory.OrderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 玉书
 * @date 2021/12/25
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderFactory orderFactory;

    @Override
    public ResultEntity createOrder(CreateOrderRequest request) {
        OrderEntity order = orderFactory.createOrder(request);
        order.save();
        return ResultEntity.buildSuccess(request.getRequestId())
                .withData(CreateOrderResponse.builder().message("ok").build());
    }
}