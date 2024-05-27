package org.sieun.inventory.domain.model;

import java.util.List;
import org.sieun.inventory.domain.model.inventory.Inventory;

public class Inventories {

    private final List<Inventory> inventories;

    public Inventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public boolean use(int quantity) {
        if (quantity > getTotalQuantity()) {
            return false;
        }
        for (Inventory inventory : inventories) {
            int left = inventory.getLeftQuantity();
            if (left <= quantity) {
                inventory.use(left);
                quantity -= left;
            } else {
                inventory.use(quantity);
                break;
            }
        }
        return true;
    }

    private int getTotalQuantity() {
        int sum = 0;
        for (Inventory inventory : inventories) {
            sum += inventory.getLeftQuantity();
        }
        return sum;
    }
}
