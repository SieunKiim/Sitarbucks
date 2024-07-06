package org.sieun.Order.service.port;

import java.util.HashMap;
import org.sieun.Order.domain.model.Ingredient;

public interface IngredientPort {
    void useIngredient(HashMap<Ingredient, Integer> ingredients) ;

    void restockIngredient(HashMap<Ingredient, Integer> map) ;
//    private void checkEnoughIngredient(HashMap<Ingredient, Integer> ingredients)
}
