package com.tojaoomy.demo.starter.validate;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author 玉书
 * @date 2021/2/3
 */
@Component
public class ParamValidator {

    /**
     * 因为是线程安全的，所以可以单例使用
     */
    @Autowired
    private  Validator validator;

    public List<String> validate(Object object) {
        ArrayList<String> result = Lists.newArrayList();

        if(Objects.isNull(object)) {
            result.add("请求对象为null");
        } else {
            Set<ConstraintViolation<Object>> violations = validator.validate(object);
            for(ConstraintViolation violation : violations) {
                String message = violation.getPropertyPath() + violation.getMessage();
                result.add(message);
            }
        }

        return result;
    }
}