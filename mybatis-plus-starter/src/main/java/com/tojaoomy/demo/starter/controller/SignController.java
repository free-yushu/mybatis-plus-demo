package com.tojaoomy.demo.starter.controller;

import com.tojaoomy.demo.api.support.ResultEntity;
import com.tojaoomy.demo.infra.sign.config.SignatureProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author
 * @date 2021/12/20
 */
@Slf4j
@RestController
@RequestMapping("/sign")
@Api(value = "签名", tags = "签名")
public class SignController {

    @Autowired
    private SignatureProperties signProperties;

    /**
     * @return
     */
    @RequestMapping(value = "/get",method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation("创建订单")
    public ResultEntity createOrderPressure() throws Exception {
        return ResultEntity.buildSuccess("").withData(signProperties.copy());
//        return ResultEntity.buildSuccess("").withData(new CglibHelper(signProperties).getTargetObject());
    }
}
