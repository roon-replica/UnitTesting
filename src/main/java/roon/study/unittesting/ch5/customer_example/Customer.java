package roon.study.unittesting.ch5.customer_example;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Customer {
    private String id;
    private String name;

    public boolean purchase(Product product, int quantity, ProductRepository productRepository){
        boolean isPurchased =  product.getQuantity() >= quantity;
        if(isPurchased){
//            product.decreaseQuantity(quantity);
            productRepository.decreaseQuantity(product.getId(),quantity); // 편의상..
        }

        return isPurchased;
    }
}
