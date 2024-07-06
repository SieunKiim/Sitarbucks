package org.sieun.Order.service;

import java.util.List;
import org.sieun.Order.domain.model.order.Order;
import org.sieun.Order.domain.model.order.OrderLine;
import org.sieun.Order.domain.modelRepository.OrderRepository;
import org.sieun.Order.infra.adaptor.IngredientRepositoryAdaptor;
import org.sieun.Order.service.port.IngredientPort;

public class OrderService {

    private final OrderRepository orderRepository;
    private final IngredientPort ingredientPort;

    public OrderService(IngredientRepositoryAdaptor ingredientPort, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.ingredientPort = ingredientPort;
    }

    // 주문 생성
    public Order newOrder(List<OrderLine> orderLineList) {
        Order order = new Order(orderLineList);
        ingredientPort.useIngredient(order.getAllIngredients());
        // 결제 요청
        // 제조 요청
        return orderRepository.save(order);
    }

    // 주문 조회
    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId);
    }

    // 주문 변경
    public Order changeOrder(long orderId, List<OrderLine> changedOrderLineLest){
        Order originalOrder = orderRepository.findById(orderId);
        Order changedOrder = new Order(changedOrderLineLest);
        if (!originalOrder.isChangeable()) {
            throw new RuntimeException("주문 변경이 불가능합니다");
        }
        ingredientPort.useIngredient(originalOrder.getDifferentIngredients(changedOrder));
        originalOrder.changeOrder(changedOrderLineLest);
        return orderRepository.update(originalOrder);
    }

    // 주문 취소
    public void cancelOrder(long orderId){
        Order order = orderRepository.findById(orderId);
        if (!order.isCancelable()) {
            throw new RuntimeException("주문을 취소할 수 없음");
        }
        ingredientPort.restockIngredient(order.getAllIngredients());
        order.cancelOrder();
        orderRepository.update(order);
    }
}
