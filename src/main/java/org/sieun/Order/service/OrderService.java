package org.sieun.Order.service;

import java.util.List;
import org.sieun.Order.domain.model.order.Order;
import org.sieun.Order.domain.model.order.OrderLine;
import org.sieun.Order.domain.modelRepository.OrderRepository;

public class OrderService {

    private final OrderRepository orderRepository;
    private final IngredientService ingredientService;

    public OrderService(IngredientService ingredientService, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.ingredientService = ingredientService;
    }

    // 주문 생성
    public Order newOrder(List<OrderLine> orderLineList) {
        Order order = new Order(orderLineList);
        ingredientService.useIngredient(order.getAllIngredients());
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
        ingredientService.useIngredient(originalOrder.getDifferentIngredients(changedOrder));
        originalOrder.changeOrder(changedOrderLineLest);
        return orderRepository.update(originalOrder);
    }

    // 주문 취소
    public void cancelOrder(long orderId){
        Order order = orderRepository.findById(orderId);
        if (!order.isCancelable()) {
            throw new RuntimeException("주문을 취소할 수 없음");
        }
        ingredientService.restockIngredient(order.getAllIngredients());
        order.cancelOrder();
        orderRepository.update(order);
    }
}
