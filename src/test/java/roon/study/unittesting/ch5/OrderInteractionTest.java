package roon.study.unittesting.ch5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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

    // order-warehouse 예제에서 order 실패 시 이메일 발송 요구사항 추가
    @Test
    public void testOrderSendsMailIfUnFilled_Using_Stub() {
        Order order = new Order(productName, 51);

        // stub 사용.
        // 이걸 mock()으로 생성해도 도구로서의 mock임. 헷갈리지 말라고 함.
        // 여기서 stub은 입력 데이터 설장만 하고 어떤 행위 검증도 안하는 반면
        // testOrderSendsMailIfUnFilled_Using_Mock()에서 mock은 verify(...).send()로 행위를 검증하고 있다는게 차이점인듯!
        MailService mailServiceStub = new MailServiceStub();
        order.setMailService(mailServiceStub);

        Warehouse warehouseMock = mock(Warehouse.class);
        when(warehouseMock.getIfEnough(anyString(), anyInt())).thenReturn(false); // 어떤 입력이 오든 false 리턴

        order.fill(warehouseMock);

        assertEquals(1, mailServiceStub.numberSent());
    }

    @Test
    public void testOrderSendsMailIfUnFilled_Using_Mock() {
        Order order = new Order(productName, 51);

        Warehouse warehouseMock = mock(Warehouse.class);
        when(warehouseMock.getIfEnough(anyString(), anyInt())).thenReturn(false); // 어떤 입력이 오든 false 리턴

        MailService mailServiceMock = mock(MailService.class);
        order.setMailService(mailServiceMock);

        order.fill(warehouseMock);

        verify(mailServiceMock, times(1)).send(any());
    }
}
