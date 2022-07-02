package com.tojaoomy.demo.api.graphqldemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 玉书
 * @date 2021/12/29
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class Item {

    private Long id;

    private String code;

    private String name;


    public static Item of() {
        return new Item();
    }
}