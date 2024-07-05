package org.sieun.Order.service;

import java.util.HashMap;
import org.sieun.Order.domain.model.Ingredient;
import org.sieun.Order.domain.model.order.Order;
import org.sieun.Order.domain.modelRepository.IngredientRepository;

public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public void useIngredient(HashMap<Ingredient, Integer> ingredients) {
        checkEnoughIngredient(ingredients);
        ingredientRepository.minusIngredients(ingredients);
    }

    public void restockIngredient(HashMap<Ingredient, Integer> map) {
        ingredientRepository.plusIngredients(map);
    }

    private void checkEnoughIngredient(HashMap<Ingredient, Integer> ingredients) {
        if(!ingredientRepository.isPossible(ingredients)){
            throw new RuntimeException("제료가 부족하여 주문할 수 없음");
        }
    }
}
