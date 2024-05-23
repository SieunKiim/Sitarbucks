package org.sieun.inventory.domain.model.inventory;

import java.time.LocalDate;
import java.util.Date;

public class InventoryId {
    private final Long stockId;
    private final LocalDate storedDate;

    public InventoryId(Long stockId) {
        this.stockId = stockId;
        this.storedDate = LocalDate.now();
    }

    public LocalDate getStoredDate() {
        return storedDate;
    }
}
