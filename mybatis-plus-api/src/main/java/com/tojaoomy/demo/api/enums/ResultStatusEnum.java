package com.tojaoomy.demo.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回状态枚举
 * @author 玉书
 * @date 2021/1/20
 */
@Getter
@AllArgsConstructor
public enum ResultStatusEnum {

    /**
     * 成功
     */
    SUCCESS("0000","success"),

    /**
     * 参数缺失
     */
    PARAMETER_MISSING("2001","参数缺失[%s]"),

    /**
     * 重复提交
     */
    REPEATED_SUBMISSION("2002","重复提交"),

    REQUEST_SIGN_NOT_VALID("4001","签名错误"),

    /**
     * 系统内部错误
     */
    SYSTEM_INTERNAL_ERROR("5000","系统内部错误"),

    /**
     * 其它原因
     */
    OTHER("","");

    /**
     * 状态码
     */
    @Setter
    private String code;

    /**
     * 状态消息
     */
    @Setter
    private String message;

    /**
     * @param parameterName 参数名称
     * @return
     */
    public String getFullMessage(String parameterName) {
        return String.format(message, parameterName);
    }

}