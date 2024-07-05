package org.sieun.Order.domain.model.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.sieun.Order.domain.model.Ingredient;

public class Order {

    private List<OrderLine> orderLines;

    private OrderStatus status;

    // 주문자 정보

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

    public void changeOrder(List<OrderLine> changedOrderLineList){
        this.orderLines = changedOrderLineList;
    }

    public void cancelOrder(){
        this.status = OrderStatus.CANCELED;
    }

    public List<OrderLine> getOrderLines(){
        return this.orderLines;
    }

    public HashMap<Ingredient, Integer> getAllIngredients() {
        HashMap<Ingredient, Integer> output = new HashMap<>();
        for (OrderLine orderLine : this.orderLines) {
            HashMap<Ingredient, Integer> totalIngredients = orderLine.getTotalIngredients();
            for (Entry<Ingredient, Integer> entry : totalIngredients.entrySet()) {
                Ingredient ingredient = entry.getKey();
                Integer quantity = entry.getValue();
                Integer sumQuantity = output.getOrDefault(ingredient, 0);
                output.put(ingredient, sumQuantity + quantity);
            }
        }
        return output;
    }

    /**
     * HashMap value 값이 음수면 돌려놓아야 하는 값, 양수면 필요로하는 값.
     */
    public HashMap<Ingredient, Integer> getDifferentIngredients(Order changeOrder) {
        HashMap<Ingredient, Integer> output = new HashMap<>();
        HashMap<Ingredient, Integer> changeIngredients = new HashMap<>(changeOrder.getAllIngredients());
        HashMap<Ingredient, Integer> ingredients = (getAllIngredients());

        for (Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            Integer quantity = entry.getValue();
            if (changeIngredients.containsKey(ingredient)) {
                int needQuantity = changeIngredients.get(ingredient) - quantity;
                output.put(ingredient, needQuantity);
            }
        }
        return output;
    }
}
