package org.sieun.inventory.domain.model.inventory;

public class Inventory {
    private final InventoryId inventoryId;
    private Integer originalQuantity;
    private Integer leftQuantity;
    private final Integer purchasePrice;
    private boolean isDisposed;



    public Inventory(Long storeId, int quantity, int purchasePrice) {
        this.inventoryId = new InventoryId(storeId);
        this.originalQuantity = quantity;
        this.purchasePrice = purchasePrice;
        this.isDisposed = false;
        this.leftQuantity = quantity;
    }

    public InventoryId getId() {
        return this.inventoryId;
    }

    public void use(int quantity) {
        leftQuantity -= quantity;
    }

    public void dispose() {
        this.isDisposed = true;
    }
}
