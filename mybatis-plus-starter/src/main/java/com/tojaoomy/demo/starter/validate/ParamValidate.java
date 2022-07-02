package com.tojaoomy.demo.starter.validate;

import com.tojaoomy.demo.api.support.ResultEntity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 玉书
 * @date 2021/2/7
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidate {

    Class returnType() default ResultEntity.class;
}