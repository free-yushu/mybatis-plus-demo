package com.tojaoomy.demo.infra.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动注入字段，需要在MybatisPlusConfiguration配置
 * @author 玉书
 * @date 2021/12/21
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("ctime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("mtime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("mtime", LocalDateTime.now(), metaObject);
    }
}