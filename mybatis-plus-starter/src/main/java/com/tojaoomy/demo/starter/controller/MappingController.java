package com.tojaoomy.demo.starter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tojaoomy.demo.infra.dataobject.OrderDO;
import com.tojaoomy.demo.infra.mapper.OrderMapper;
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
@RequestMapping("/mapping")
public class MappingController {

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/counts")
    public Long getCount() {
        LambdaQueryWrapper<OrderDO> wrapper = new LambdaQueryWrapper<OrderDO>()
                .eq(OrderDO::getDeleted, 0);
        return orderMapper.selectCount(wrapper);
    }
}
