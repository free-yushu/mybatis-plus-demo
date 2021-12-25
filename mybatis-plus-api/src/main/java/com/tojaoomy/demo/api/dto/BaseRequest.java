package com.tojaoomy.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author 玉书
 * @date 2021/1/20
 */
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {


    /**
     * 请求流水号
     */
    @NotNull(message = "requestId不能为null")
    @NotBlank(message = "requestId不能为空")
    private String requestId;

}