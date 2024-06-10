package org.sieun.Order.domain.model.order;

import java.util.List;

public class Order {
    private final List<OrderLine> orderLines;

    private final OrderStatus status;

    public Order(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
        this.status = OrderStatus.TEST;
    }

}
