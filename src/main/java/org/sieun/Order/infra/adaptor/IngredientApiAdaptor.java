package org.sieun.Order.infra.adaptor;

import java.util.HashMap;
import org.sieun.Order.domain.model.Ingredient;
import org.sieun.Order.service.port.IngredientPort;

/**
 * 추후 재료관련 도메인이 MSA구조의 별개 서버로 운용될 때 구현체 적용
 */
public class IngredientApiAdaptor implements IngredientPort {

    @Override
    public void useIngredient(HashMap<Ingredient, Integer> ingredients) {
        // 인그리디언트 API (요청)
    }

    @Override
    public void restockIngredient(HashMap<Ingredient, Integer> map) {

    }
}
