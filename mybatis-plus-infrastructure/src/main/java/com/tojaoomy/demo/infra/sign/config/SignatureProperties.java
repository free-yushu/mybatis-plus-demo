package com.tojaoomy.demo.infra.sign.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 腾讯云COS配置类
 * @author 玉书
 * @date 2021/1/25
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.signature")
@PropertySource(value = {"classpath:config/signature.properties", "file:${CONF_PATH}/signature.properties"}, ignoreResourceNotFound = true)
public class SignatureProperties {

    @Getter
    @Setter
    private SignatureProperty  payment;

    @Getter
    @Setter
    private SignatureProperty trade;

    public SignatureProperties copy() {
        SignatureProperties copy = new SignatureProperties();
        copy.setPayment(payment);
        copy.setTrade(trade);
        return copy;
    }

    @Data
    public static class SignatureProperty {
        /**
         * appkey
         */
        private String appkey;

        /**
         * secretKey
         */
        private String secretKey;

        /**
         * 是否需要签名
         */
        private boolean needCheckSign;

        /**
         * debug签名，返回签名值
         */
        private boolean debugSign;
    }

}