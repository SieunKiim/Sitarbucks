package org.sieun.inventory.infra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.sieun.inventory.domain.model.inventory.Inventory;
import org.sieun.inventory.domain.model.inventory.InventoryId;
import org.sieun.inventory.domain.modelRepository.InventoryRepository;

public class MemoryInventoryRepository implements InventoryRepository {

    private final HashMap<InventoryId, Inventory> repository = new HashMap<>();

    @Override
    public List<Inventory> getInventories(InventoryId inventoryId) {
        List<Inventory> output = new ArrayList<>();
        for (Entry<InventoryId, Inventory> entry : repository.entrySet()) {
            if (entry.getKey().isSameStock(inventoryId)) {
                output.add(entry.getValue());
            }
        }
        return output;
    }

    @Override
    public Inventory save(Inventory inventory) {
        repository.put(inventory.getId(), inventory);
        return inventory;
    }

    @Override
    public List<Inventory> getDisposeTargets(LocalDate today) {
        List<Inventory> output = new ArrayList<>();
        for (Entry<InventoryId, Inventory> entry : repository.entrySet()) {
            LocalDate expireDate = entry.getKey().getStoredDate() ; // + 사용 가능 기간
            if (expireDate.isBefore(LocalDate.now())) {
                output.add(entry.getValue());
            }
        }
        return output;
    }
}
