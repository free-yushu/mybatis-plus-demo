package com.tojaoomy.graphql.demo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @author 玉书
 * @date 2021/12/28
 */
@Data
@AllArgsConstructor
public class User {

    private Long id;

    private String name;

    private Integer age;

}