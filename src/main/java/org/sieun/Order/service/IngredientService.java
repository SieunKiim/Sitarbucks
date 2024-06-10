package org.sieun.Order.service;

import java.util.HashMap;
import org.sieun.Order.domain.model.Ingredient;
import org.sieun.Order.domain.modelRepository.IngredientRepository;

public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public void checkPossible(HashMap<Ingredient, Integer> map) {

    }
}
