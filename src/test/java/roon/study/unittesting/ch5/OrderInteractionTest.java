package roon.study.unittesting.ch5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class OrderInteractionTest {
    private static String productName = "product1";

    @Test
    public void testFillingRemovesInventoryIfInStock() {
        // setup - data
        Order order = new Order(productName, 50);
        Warehouse warehouseMock = mock(Warehouse.class);

        // setup - expectations
        when(warehouseMock.getInventory(productName)).thenReturn(50);
        when(warehouseMock.getIfEnough(productName, 50)).thenReturn(true);

        // exercise
        order.fill(warehouseMock);

        // verify
        assertTrue(order.isFilled());

        // mock은 state를 검증하는게 하니라 behavior를 검증
        // setup에서 mock에게 기대하는 바를 설정하고나서 검증
    }

    @Test
    public void testFillingDoesNotRemoveIfNotEnoughInStock() {
        Order order = new Order(productName, 51);
        Warehouse warehouseMock = mock(Warehouse.class);

        when(warehouseMock.getIfEnough(anyString(), anyInt())).thenReturn(false); // 어떤 입력이 오든 false 리턴

        order.fill(warehouseMock);

        assertFalse(order.isFilled());
    }
}
