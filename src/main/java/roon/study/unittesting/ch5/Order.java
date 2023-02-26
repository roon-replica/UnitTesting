package roon.study.unittesting.ch5;

public class Order {
    private String productName;
    private int count;
    private boolean isFilled;
    private MailService mailService;

    public Order(String productName, int count) {
        this.productName = productName;
        this.count = count;
        this.isFilled = false;
    }

    public void setMailService(MailService mailService){
        this.mailService = mailService;
    }

    public void fill(Warehouse warehouse) {
        isFilled = warehouse.getIfEnough(this.productName, this.count);
        if(!isFilled) mailService.send(new MailService.Message());
    }

    public boolean isFilled() {
        return isFilled;
    }
}
