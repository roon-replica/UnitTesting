package roon.study.unittesting.ch3;

import java.time.LocalDate;

public class Delivery{
    private LocalDate deliveryDate;
    private LocalDate theSoonestDeliveryDate;

    public Delivery(LocalDate deliveryDate){
        this.deliveryDate = deliveryDate;
        this.theSoonestDeliveryDate = deliveryDate.plusDays(2);
    }

    public boolean isDeliveryDateValid(LocalDate deliveryDate){
        return deliveryDate.isEqual(theSoonestDeliveryDate) || deliveryDate.isEqual(theSoonestDeliveryDate);
    }
}
