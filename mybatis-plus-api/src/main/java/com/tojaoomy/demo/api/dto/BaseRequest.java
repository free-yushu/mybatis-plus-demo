package com.tojaoomy.demo.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
    @ApiModelProperty(value = "请求流水号", dataType = "string", required = true)
    private String requestId;

    /**
     * 签名key
     */
    private String appkey;

    /**
     * 请求时间
     */
    private Long ts;

    /**
     * 签名(md5)
     */
    private String sign;
}