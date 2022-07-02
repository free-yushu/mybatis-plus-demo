package com.tojaoomy.demo.infra.repository;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tojaoomy.demo.api.graphqldemo.dto.Item;
import com.tojaoomy.demo.api.graphqldemo.dto.Param;

/**
 * @author 玉书
 * @date 2021/12/29
 */
public class ItemResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private IItemService itemService;

    public ItemResolver(IItemService itemService) {
        this.itemService = itemService;
    }

    public Item queryById(Long id) {
        return itemService.queryById(id);
    }

    public Item updateName(Param param) {
        return itemService.updateName(param);
    }
}
