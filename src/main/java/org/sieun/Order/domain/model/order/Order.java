package org.sieun.Order.domain.model.order;

import java.util.List;

public class Order {

    private List<OrderLine> orderLines;

    private OrderStatus status;

    public Order(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
        this.status = OrderStatus.RECEIVED;
    }

    public boolean isChangeable() {
        return this.status == OrderStatus.RECEIVED;
    }

    public boolean isCancelable(){
        return this.status == OrderStatus.RECEIVED;
    }

    public void changeOrder(List<OrderLine> orderLines){
        this.orderLines = orderLines;
    }

    public void cancelOrder(){
        this.status = OrderStatus.CANCELED;
    }

}
