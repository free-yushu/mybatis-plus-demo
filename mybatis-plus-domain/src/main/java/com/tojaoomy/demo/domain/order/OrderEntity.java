package com.tojaoomy.demo.domain.order;

import com.tojaoomy.demo.domain.order.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
@Component()
public class OrderEntity {

    private String orderId;

    private Long pkId;

    private IOrderRepository orderRepository;

    private IOrderRepository orderRepository2;

    public void save() {
        //
        orderRepository.save(this);
    }

}