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
@ConfigurationProperties(prefix = "spring.sign")
@PropertySource(value = {"classpath:config/sign.properties", "file:${CONF_PATH}/sign.properties"}, ignoreResourceNotFound = true)
public class SignProperties {

    @Getter
    @Setter
    private SignProperty app1Sign;

    @Getter
    @Setter
    private SignProperty app2Sign;

    public SignProperties copy() {
        SignProperties copy = new SignProperties();
        copy.setApp1Sign(app1Sign);
        copy.setApp2Sign(app2Sign);
        return copy;
    }

    @Data
    static class SignProperty {
        /**
         * appkey
         */
        private String appkey;

        /**
         * secretKey
         */
        private String secretKey;
    }

}