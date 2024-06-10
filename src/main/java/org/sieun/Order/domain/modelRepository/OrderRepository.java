package org.sieun.Order.domain.modelRepository;

import org.sieun.Order.domain.model.order.Order;

public interface OrderRepository {

    Order save(Order order);
}
