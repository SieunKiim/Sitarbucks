package org.sieun.Order.domain.model.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.sieun.Order.domain.model.Ingredient;

public class OrderLines {

    private final List<OrderLine> orderLines;

    public OrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
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
}
