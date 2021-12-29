package com.tojaoomy.demo.infra.repository;

import com.google.common.collect.Maps;
import com.tojaoomy.demo.api.graphqldemo.dto.Item;
import com.tojaoomy.demo.api.graphqldemo.dto.Param;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 玉书
 * @date 2021/12/29
 */
@Service
public class ItemService implements IItemService {

    private Map<Long, Item> data = Maps.newHashMap();

    {
        data.put(1L, new Item(1L, "T1", "item1"));
        data.put(2L, new Item(2L, "T2", "item2"));
        data.put(3L, new Item(3L, "T3", "item3"));
        data.put(4L, new Item(4L, "T4", "item4"));
    }

    @Override
    public Item queryById(Long id) {
        return data.get(id);
    }

    @Override
    public Item updateName(Param param) {
        Item item = data.get(param.getId());
        if(item != null) {
            item.setName(param.getName());
        }
        return item;
    }
}