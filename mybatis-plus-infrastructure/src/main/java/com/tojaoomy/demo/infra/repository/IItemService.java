package com.tojaoomy.demo.infra.repository;

import com.tojaoomy.demo.api.graphqldemo.dto.Item;
import com.tojaoomy.demo.api.graphqldemo.dto.Param;

/**
 * @author 玉书
 * @date 2021/12/29
 */
public interface IItemService {

    public Item queryById(Long id);

    public Item updateName(Param param);
}