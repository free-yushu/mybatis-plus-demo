package com.tojaoomy.graphql.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 玉书
 * @date 2021/12/28
 */
@Data
@AllArgsConstructor
public class Order {

    private String orderId;

    private String productName;

    private BigDecimal money;

}