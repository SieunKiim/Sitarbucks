package org.sieun.Order.infra.adaptor;

import java.util.HashMap;
import org.sieun.Order.domain.model.Ingredient;
import org.sieun.Order.domain.modelRepository.IngredientRepository;
import org.sieun.Order.service.port.IngredientPort;

public class IngredientRepositoryAdaptor implements IngredientPort {
    private final IngredientRepository ingredientRepository;

    public IngredientRepositoryAdaptor(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void useIngredient(HashMap<Ingredient, Integer> ingredients) {
        checkEnoughIngredient(ingredients);
        ingredientRepository.minusIngredients(ingredients);
    }

    @Override
    public void restockIngredient(HashMap<Ingredient, Integer> map) {
        ingredientRepository.plusIngredients(map);
    }

    private void checkEnoughIngredient(HashMap<Ingredient, Integer> ingredients) {
        if(!ingredientRepository.isPossible(ingredients)){
            throw new RuntimeException("제료가 부족하여 주문할 수 없음");
        }
    }
}
