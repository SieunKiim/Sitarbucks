package org.sieun.Order.domain.model;

import java.util.HashMap;

public class Product {
    private final HashMap<Ingredient, Integer> ingredients;

    public Product(HashMap<Ingredient, Integer> ingredients){
        this.ingredients = ingredients;
    }

    public HashMap<Ingredient, Integer> getIngredients(){
        return this.ingredients;
    }
}
