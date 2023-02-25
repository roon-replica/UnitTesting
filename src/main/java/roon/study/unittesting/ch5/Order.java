package roon.study.unittesting.ch5;

public class Order {
    private String productName;
    private int count;
    private boolean isFilled;

    public Order(String productName, int count) {
        this.productName = productName;
        this.count = count;
        this.isFilled = false;
    }

    public void fill(Warehouse warehouse) {
        isFilled = warehouse.getIfEnough(this.productName, this.count);
    }

    public boolean isFilled() {
        return isFilled;
    }
}
