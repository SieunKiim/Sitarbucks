package org.sieun.Order.service;

import java.util.HashMap;
import java.util.List;
import org.sieun.Order.domain.model.Ingredient;
import org.sieun.Order.domain.model.order.Order;
import org.sieun.Order.domain.model.order.OrderLine;
import org.sieun.Order.domain.model.order.OrderLines;
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
        OrderLines orderLines = new OrderLines(orderLineList);
        HashMap<Ingredient, Integer> map = orderLines.getAllIngredients();
        ingredientService.checkPossible(map);
        Order order = new Order(orderLineList);
        return orderRepository.save(order);
    }

    // 주문 조회
    public Order getOrder(long orderId) {
        return orderRepository.findById(orderId);
    }

    // 주문 변경
    public Order changeOrder(long orderId, List<OrderLine> orderLineList){
        Order order = orderRepository.findById(orderId);
        if (!order.isChangeable()) {
            throw new RuntimeException("주문을 변경할 수 없음");
        }
        order.changeOrder(orderLineList);
        return orderRepository.update(orderId, order);
    }

    // 주문 취소
    public void cancelOrder(long orderId){
        Order order = orderRepository.findById(orderId);
        if (!order.isCancelable()) {
            throw new RuntimeException("주문을 취소할 수 없음");
        }
        order.cancelOrder();
        orderRepository.update(orderId, order);
    }
}
