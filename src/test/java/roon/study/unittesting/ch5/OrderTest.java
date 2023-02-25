package roon.study.unittesting.ch5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Warehouse warehouse = new WarehouseImpl();

    @BeforeEach
    public void setUp() {
        warehouse.add("product1", 50);
        warehouse.add("product2", 25);
    }

    @Test
    public void testOrderIsFilledIfEnoughInWarehouse() {
        String productName = "product1";
        Order order = new Order(productName, 50);
        order.fill(warehouse);

        // state verification
        assertTrue(order.isFilled());
        assertEquals(0, warehouse.getInventory(productName));
    }

    @Test
    public void testOrderDoesNotRemoveIfNotEnough() {
        String productName = "product1";
        Order order = new Order(productName, 50 + 1);
        order.fill(warehouse);

        // state verification
        assertFalse(order.isFilled());
        assertEquals(50, warehouse.getInventory(productName));
    }

}