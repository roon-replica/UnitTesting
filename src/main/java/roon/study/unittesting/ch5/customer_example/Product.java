package roon.study.unittesting.ch5.customer_example;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
    private String id;
    private String name;
    private int quantity;

    public void decreaseQuantity(int quantity){
        this.quantity-=quantity;
    }

}
