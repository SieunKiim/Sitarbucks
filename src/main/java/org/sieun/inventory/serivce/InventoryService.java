package org.sieun.inventory.serivce;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import org.sieun.inventory.domain.model.inventory.Inventory;

public class InventoryService {

    public void restock() { // inventory save();
        Inventory inventory = new Inventory(); // repository에서 가져왔다고 치자. Repository.getInvetory()

    }
}
