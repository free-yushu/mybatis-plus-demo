package com.tojaoomy.demo.starter.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tojaoomy.demo.api.enums.ResultStatusEnum;
import com.tojaoomy.demo.api.order.dto.CreateOrderRequest;
import com.tojaoomy.demo.api.support.ResultEntity;
import com.tojaoomy.demo.application.order.service.IOrderService;
import com.tojaoomy.demo.infra.dataobject.OrderDO;
import com.tojaoomy.demo.infra.mapper.OrderMapper;
import com.tojaoomy.demo.starter.validate.ParamValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author
 * @date 2021/12/20
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ParamValidate
    @RequestMapping("/create")
    public ResultEntity createOrder(CreateOrderRequest request) {
        try {
            return orderService.createOrder(request);
        } catch (Throwable e) {
            log.error("<<< createOrder error on params:[{}]", JSON.toJSONString(request), e);
            return ResultEntity.of(request.getRequestId(), ResultStatusEnum.SYSTEM_INTERNAL_ERROR);
        }
    }
}
