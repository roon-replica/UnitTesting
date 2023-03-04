package roon.study.unittesting.ch5.customer_example;

import org.junit.jupiter.api.Test;
import roon.study.unittesting.ch5.MailService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    // 식별할 수 있는 동작
    // 시스템 사이의 통신
    // mock으로 처리해도 괜찮다
    @Test
    public void successful_purchase() {
        var mailServiceMock = mock(MailService.class);
        var customerRepositoryMock = mock(CustomerRepository.class);
        var productRepositoryMock = mock(ProductRepository.class);

        when(customerRepositoryMock.getCustomer("sampleCustomerId")).thenReturn(new Customer("sampleCustomerId", "name"));
        when(productRepositoryMock.getProduct("sampleProductId")).thenReturn(new Product("sampleProductId", "sampleProduct", 10));

        CustomerController sut = new CustomerController(mailServiceMock, customerRepositoryMock, productRepositoryMock);

        boolean isSuccess = sut.purchase("sampleCustomerId", "sampleProductId", 10);

        assertTrue(isSuccess);
        verify(mailServiceMock, times(1)).send(any());
    }

    // 시스템 내부 통신
    @Test
    public void purchase_succeeds_when_enough_inventory() {
        var productRepositoryMock = mock(ProductRepository.class);
        var sampleProduct = new Product("sampleProductId", "sampleProduct", 10);
        when(productRepositoryMock.getProduct(sampleProduct.getId())).thenReturn(sampleProduct);

        var customer = new Customer("id", "name");
        int purchaseCount = 5;
        boolean isSuccess = customer.purchase(sampleProduct, purchaseCount, productRepositoryMock);

        assertTrue(isSuccess);
        verify(productRepositoryMock).decreaseQuantity(sampleProduct.getId(), purchaseCount);
        // 최종 걀과가 아닌 중간 연산을 검증하고 있다.
        // decreaseQuantity()는 클라이언트가 목표룰 달성하는데 도움이 되는 연산이나 상태가 아니다
        // 그래서 시스템 내부 통신에는 mock 쓰면 구현 세부 사항과 결합되어 리팩터링 내성 낮아짐.
    }

}