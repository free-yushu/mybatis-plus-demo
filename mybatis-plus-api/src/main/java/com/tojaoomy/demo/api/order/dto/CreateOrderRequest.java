package com.tojaoomy.demo.api.order.dto;

import com.tojaoomy.demo.api.dto.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

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
public class CreateOrderRequest extends BaseRequest {

    @NotBlank
    private String orderId;

}