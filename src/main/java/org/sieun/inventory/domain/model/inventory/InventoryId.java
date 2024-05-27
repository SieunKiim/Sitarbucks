package org.sieun.inventory.domain.model.inventory;

import java.time.LocalDate;
import java.util.Objects;

public class InventoryId {
    private final Long stockId;
    private final LocalDate storedDate;

    public InventoryId(Long stockId) {
        this.stockId = stockId;
        this.storedDate = LocalDate.now();
    }

    public InventoryId(Long stockId, LocalDate localDate) {
        this.stockId = stockId;
        this.storedDate = localDate;
    }

    public LocalDate getStoredDate() {
        return storedDate;
    }

    public boolean isSameStock(InventoryId inventoryId) {
        return Objects.equals(this.stockId, inventoryId.stockId);
    }
}
