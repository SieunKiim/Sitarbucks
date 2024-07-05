package org.sieun.Order.domain.modelRepository;

import java.util.HashMap;
import org.sieun.Order.domain.model.Ingredient;

public interface IngredientRepository {

    boolean isPossible(HashMap<Ingredient, Integer> map);

    void plusIngredients(HashMap<Ingredient, Integer> map);

    void minusIngredients(HashMap<Ingredient, Integer> map);
}
