package org.sieun.inventory.serivce;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sieun.inventory.domain.model.inventory.Inventory;
import org.sieun.inventory.domain.model.inventory.InventoryId;
import org.sieun.inventory.domain.modelRepository.InventoryRepository;
import org.sieun.inventory.infra.MemoryInventoryRepository;

class InventoryServiceTest {

    private final InventoryRepository inventoryRepository = new MemoryInventoryRepository();
    private final InventoryService inventoryService = new InventoryService(inventoryRepository);
    private final InventoryId id = new InventoryId(1L);

    @BeforeEach
    void initInventoryServiceTest() {
        Inventory inventory1 = new Inventory(1L, LocalDate.of(2024, 10, 3), 10, 3000);
        inventoryRepository.save(inventory1);
        Inventory inventory2 = new Inventory(1L, LocalDate.of(2024, 10, 6), 10, 3000);
        inventoryRepository.save(inventory2);
    }

    @Test
    @DisplayName("인벤토리 사용")
    void useInventory() {
        List<Inventory> inventories = inventoryRepository.getInventories(id);
        boolean firstResult = inventoryService.useInventory(id, 5);
        assertThat(firstResult).isEqualTo(true);
        assertThat(inventories.stream()
                .reduce(0, (leftQuantity, inventory) -> leftQuantity + inventory.getLeftQuantity(),
                        Integer::sum)).isEqualTo(15);

        boolean secondResult = inventoryService.useInventory(id, 7);
        assertThat(secondResult).isEqualTo(true);
        assertThat(inventories.stream()
                .reduce(0, (leftQuantity, inventory) -> leftQuantity + inventory.getLeftQuantity(),
                        Integer::sum)).isEqualTo(8);

        boolean thirdResult = inventoryService.useInventory(id, 9);
        assertThat(thirdResult).isEqualTo(false);
    }

    @Test
    @DisplayName("인벤토리 저장")
    void fillInventory() {
        inventoryService.fillInventory(1L, 7, 400);
        assertThat(inventoryRepository.getInventories(id).stream()
                .reduce(0, (leftQuantity, inventory) -> leftQuantity + inventory.getLeftQuantity(),
                        Integer::sum)).isEqualTo(27);
    }

    @Test
    @DisplayName("인벤토리 폐기")
    void disposeInventory() {
        Inventory inventory_old = new Inventory(1L, LocalDate.now().plusDays(-1), 10, 3000);
        Inventory inventory_fresh = new Inventory(1L, LocalDate.now().plusDays(1), 10, 3000);
        inventoryRepository.save(inventory_old);
        inventoryRepository.save(inventory_fresh);
        inventoryService.disposeInventory();

        assertThat(inventory_old.isDisposed()).isEqualTo(true);
        assertThat(inventory_fresh.isDisposed()).isEqualTo(false);
    }

    @Test
    @DisplayName("인벤토리 조회")
    void getInventory() {
        List<Inventory> inventory = inventoryService.getInventory(id);
        assertThat(inventory.size()).isEqualTo(2);
    }
}