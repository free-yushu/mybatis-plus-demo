package com.tojaoomy.demo.domain.order;

import com.tojaoomy.demo.domain.order.repository.IOrderRepository;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @author 玉书
 * @date 2021/12/25
 */
@Data
@With
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class OrderAggregation {

    private String orderId;

    private Long pkId;

    private IOrderRepository orderRepository;

    public void save() {
        orderRepository.save(this);
    }

}