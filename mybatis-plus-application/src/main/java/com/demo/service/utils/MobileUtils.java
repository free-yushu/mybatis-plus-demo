package com.demo.service.utils;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @author 玉书
 * @date 2022/7/15
 */
public class MobileUtils {

    public static boolean validate(String mobileNumber) {
        if(mobileNumber == null) {
            return false;
        }
        if(!mobileNumber.startsWith("1")) {
            return false;
        }
        // 判断 手机号码 和座机

        return true;
    }
}