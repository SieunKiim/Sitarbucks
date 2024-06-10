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

    public Order newOrder(List<OrderLine> orderLineList) {
        OrderLines orderLines = new OrderLines(orderLineList);
        HashMap<Ingredient, Integer> map = orderLines.getAllIngredients();
        ingredientService.checkPossible(map);
        Order order = new Order(orderLineList);
        return orderRepository.save(order);
    }
}
