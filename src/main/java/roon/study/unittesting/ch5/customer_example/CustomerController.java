package roon.study.unittesting.ch5.customer_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import roon.study.unittesting.ch5.MailService;

@RestController
public class CustomerController {
    private MailService mailService;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    public CustomerController(MailService mailService, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.mailService = mailService;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/purchase")
    public boolean purchase(String customerId, String productId, int quantity) {
        Customer customer = customerRepository.getCustomer(customerId);
        Product product = productRepository.getProduct(productId);

        boolean isPurchased = customer.purchase(product, quantity, productRepository);
        productRepository.save(product); // 편의상..


        if (isPurchased) {
            mailService.send(new MailService.Message());
        }

        return isPurchased;
    }
}
