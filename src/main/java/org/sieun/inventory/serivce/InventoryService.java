package org.sieun.inventory.serivce;

import java.time.LocalDate;
import java.util.List;
import org.sieun.inventory.domain.model.Inventories;
import org.sieun.inventory.domain.model.inventory.Inventory;
import org.sieun.inventory.domain.model.inventory.InventoryId;
import org.sieun.inventory.domain.modelRepository.InventoryRepository;

public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getInventory(InventoryId inventoryId) {
        return inventoryRepository.getInventories(inventoryId);
    }

    public boolean useInventory(InventoryId inventoryId, int quantity) {
        Inventories inventories = new Inventories(inventoryRepository.getInventories(inventoryId));
        return inventories.use(quantity);
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
