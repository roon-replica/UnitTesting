package roon.study.unittesting.ch5.customer_example;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    private static Map<String, Product> repository = new HashMap<>(Map.of("sampleProductId", new Product("sampleProductId", "sample", 10)));

    public Product getProduct(String productId) {
        return repository.get(productId);
    }

    public void save(Product product) {
        repository.put(product.getId(), product);
    }

    public void decreaseQuantity(String productId, int quantity){
        Product product = repository.get(productId);
        if(product == null) throw new IllegalArgumentException("product not found");

        product.decreaseQuantity(quantity);
        repository.put(productId, product);
    }
}
