package com.tojaoomy.demo.api.order.dto;

import com.tojaoomy.demo.api.dto.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

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

    @NotNull(message = "orderId不能为null")
    @NotBlank(message = "orderId不能为空")
    private String orderId;

}