package com.tojaoomy.demo.api.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Accessors;

/**
 * @author 玉书
 * @date 2021/12/25
 */
@Data
@With
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class CreateOrderResponse {

    private String message;

}