package org.sieun.inventory.domain.modelRepository;

import java.time.LocalDate;
import java.util.List;
import org.sieun.inventory.domain.model.inventory.Inventory;
import org.sieun.inventory.domain.model.inventory.InventoryId;

public interface InventoryRepository {

    Inventory get(InventoryId inventoryId);

    Inventory save(Inventory inventory);

    List<Inventory> getDisposeTargets(LocalDate today);
}
