package org.sieun.Order.infra.adaptor;

import java.util.HashMap;
import org.sieun.Order.domain.model.Ingredient;
import org.sieun.Order.service.port.IngredientPort;

public class IngredientServiceAdaptor implements IngredientPort {
    public IngredientServiceAdaptor(){

    }

    @Override
    public void useIngredient(HashMap<Ingredient, Integer> ingredients) {

    }

    @Override
    public void restockIngredient(HashMap<Ingredient, Integer> map) {

    }
}
