package roon.study.unittesting.ch5.customer_example;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {
    private static Map<String, Customer> repository = new HashMap<>(Map.of("sampleCustomerId", new Customer("sampleCustomerId", "sample")));

    public Customer getCustomer(String customerId){
        return repository.get(customerId);
    }
}
