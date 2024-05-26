package org.sieun.inventory.domain;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sieun.inventory.domain.model.inventory.Inventory;

public class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void initInventory() {
        inventory = new Inventory(1L, 10, 3000);
    }

    @Test
    void use() {
        inventory.use(4);
        assertThat(inventory.getLeftQuantity()).isEqualTo(6);
    }

    @Test
    void dispose() {
        assertThat(inventory.isDisposed()).isEqualTo(false);
        inventory.dispose();
        assertThat(inventory.isDisposed()).isEqualTo(true);
    }
}
