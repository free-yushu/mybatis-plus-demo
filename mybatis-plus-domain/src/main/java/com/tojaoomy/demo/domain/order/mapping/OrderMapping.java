package com.tojaoomy.demo.domain.order.mapping;

import com.tojaoomy.demo.domain.BaseMapping;
import com.tojaoomy.demo.domain.order.OrderEntity;
import com.tojaoomy.demo.infra.dataobject.OrderDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * componentModel = spring可以被spring管理
 * @author 玉书
 * @date 2021/2/7
 */
@Mapper(componentModel = "spring")
public interface OrderMapping extends BaseMapping<OrderDO, OrderEntity> {

    @Override
    @Mapping(source = "id", target = "pkId")
    OrderEntity toDomain(OrderDO dataObject);

    @Override
    @Mapping(source = "pkId", target = "id")
    OrderDO toDataObject(OrderEntity domain);
}