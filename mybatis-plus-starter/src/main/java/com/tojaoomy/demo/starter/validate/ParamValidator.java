package com.tojaoomy.demo.starter.validate;

import com.google.common.collect.Lists;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 玉书
 * @date 2021/2/3
 */
public class ParamValidator {

    /**
     * 因为是线程安全的，所以可以单例使用
     */
    private static final Validator VALIDATOR = new Validator();

    public static List<String> validate(Object object) {
        ArrayList<String> result = Lists.newArrayList();

        if(Objects.isNull(object)) {
            result.add("请求对象为null");
        } else {
            List<ConstraintViolation> violations = VALIDATOR.validate(object);
            for(ConstraintViolation violation : violations) {
                String message = violation.getMessage();
                int indexOf = message.lastIndexOf(".");
                if (indexOf > 0) {
                    message = message.substring(indexOf + 1);
                }
                result.add(message);
            }
        }

        return result;
    }
}