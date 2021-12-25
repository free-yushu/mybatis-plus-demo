package com.tojaoomy.demo.domain.order;

import com.tojaoomy.demo.domain.order.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Accessors;

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