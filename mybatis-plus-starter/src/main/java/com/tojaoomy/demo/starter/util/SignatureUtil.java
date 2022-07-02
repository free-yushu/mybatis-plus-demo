package com.tojaoomy.demo.starter.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TimeZone;
import java.util.TreeMap;

/**
 * @author hejian
 * @Date 2020/7/28
 */
@Slf4j
public class SignatureUtil {

    /**
     * 生成签名
     */
    public static String generateSign(Object obj, String secretKey) {
        Preconditions.checkNotNull(obj, "签名对象不能为null");
        Map<String, Object> map;
        if (obj instanceof Map) {
            map = (Map<String, Object>) obj;
        } else {
            map = beanToMap(obj);
        }
        return generateSign(map, secretKey);
    }

    /**
     * 计算签名
     *
     * @param treeMap   参数Map
     * @param secretKey 签名密钥
     * @return
     */
    public static String generateSign(Map<String, Object> treeMap, String secretKey) {
        try {
            StringJoiner joiner = new StringJoiner("&");
            for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                if ("sign".equals(entry.getKey())) {
                    continue;
                }
                joiner.add(String.format("%s=%s", entry.getKey(), URLEncoder.encode(entry.getValue().toString(), "utf-8")));
            }

            String sign = DigestUtils.md5Hex(String.format("%s%s", joiner, secretKey));
//            log.info(">>> calc sign [{}]", sign);
            return sign;
        } catch (Exception e) {
            log.error("calc sign error, treeMap: {}", treeMap, e);
            return null;
        }
    }

    /**
     * 将对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        JSON.defaultTimeZone  = TimeZone.getDefault();
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        Map<String, Object> objectMap = JSONObject.parseObject(JSON.toJSONString(bean));
        objectMap.entrySet().stream().forEach(entry -> {
            sortedMap.put(entry.getKey(), entry.getValue());
        });
        return sortedMap;
    }

}
