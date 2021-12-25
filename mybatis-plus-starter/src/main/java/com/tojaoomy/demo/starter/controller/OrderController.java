package com.tojaoomy.demo.starter.controller;

import com.alibaba.fastjson.JSON;
import com.tojaoomy.demo.api.enums.ResultStatusEnum;
import com.tojaoomy.demo.api.order.dto.CreateOrderRequest;
import com.tojaoomy.demo.api.support.ResultEntity;
import com.tojaoomy.demo.application.order.service.IOrderService;
import com.tojaoomy.demo.starter.validate.ParamValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "订单服务", tags = "Order控制器")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ParamValidate
    @RequestMapping("/create")
    @ApiOperation("创建订单")
    public ResultEntity createOrder(CreateOrderRequest request) {
        try {
            return orderService.createOrder(request);
        } catch (Throwable e) {
            log.error("<<< createOrder error on params:[{}]", JSON.toJSONString(request), e);
            return ResultEntity.of(request.getRequestId(), ResultStatusEnum.SYSTEM_INTERNAL_ERROR);
        }
    }
}
