package com.tojaoomy.demo.application.order.service;

import com.tojaoomy.demo.api.order.dto.CreateOrderRequest;
import com.tojaoomy.demo.api.support.ResultEntity;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @author 玉书
 * @date 2021/12/25
 */
public interface IOrderService {

    public ResultEntity createOrder(CreateOrderRequest request);
}