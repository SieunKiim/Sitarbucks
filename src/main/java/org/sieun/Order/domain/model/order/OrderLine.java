package org.sieun.Order.domain.model.order;

import java.util.HashMap;
import java.util.Map.Entry;
import org.sieun.Order.domain.model.Ingredient;
import org.sieun.Order.domain.model.Product;

public class OrderLine {
    private final Product product;
    private final Integer quantity;

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public HashMap<Ingredient, Integer> getTotalIngredients() {
        HashMap<Ingredient, Integer> output = new HashMap<>();
        HashMap<Ingredient, Integer> ingredients = this.product.getIngredients();
        for (Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
            output.put(entry.getKey(), entry.getValue() * quantity);
        }
        return output;
    }
}
