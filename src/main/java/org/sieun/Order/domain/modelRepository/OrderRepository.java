package org.sieun.Order.domain.modelRepository;

import org.sieun.Order.domain.model.order.Order;
import org.sieun.Order.infra.entity.OrderEntity;

public interface OrderRepository {

    OrderEntity findById(long orderId);

    Order save(Order order);

    Order update(long orderId, Order order);
}
