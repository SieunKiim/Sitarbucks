package org.sieun.inventory.serivce;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.time.LocalDate;
import java.util.List;
import org.sieun.inventory.domain.model.inventory.Inventory;
import org.sieun.inventory.domain.model.inventory.InventoryId;
import org.sieun.inventory.domain.modelRepository.InventoryRepository;

public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory getInventory(InventoryId inventoryId) {
        return inventoryRepository.get(inventoryId);
    }

    public void useInventory(InventoryId inventoryId, int quantity) {
        Inventory inventory = inventoryRepository.get(inventoryId);
        inventory.use(quantity);
    }

    public Inventory fillInventory(Long storeId, int quantity, int purchasePrice) {
        Inventory inventory = new Inventory(storeId, quantity, purchasePrice);
        return inventoryRepository.save(inventory);
    }

    public void disposeInventory() {
        LocalDate today = LocalDate.now();
        List<Inventory> disposeTargets = inventoryRepository.getDisposeTargets(today);
        disposeTargets.forEach(Inventory::dispose);
    }
}
