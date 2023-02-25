package roon.study.unittesting.ch5;

import java.util.HashMap;
import java.util.Map;

public interface Warehouse {
    void add(String productName, int count);

    boolean getIfEnough(String productName, int count);

    int getInventory(String productName);
}

class WarehouseImpl implements Warehouse {
    Map<String, Integer> stocks = new HashMap<>();

    @Override
    public void add(String productName, int count) {
        stocks.put(productName, stocks.getOrDefault(productName, 0) + count);
    }

    @Override
    public boolean getIfEnough(String productName, int count) {
        int stockCount = stocks.getOrDefault(productName, 0);
        boolean isEnough = stockCount >= count;

        if(isEnough) stocks.put(productName, Math.max(stockCount - count, 0));

        return isEnough;
    }

    @Override
    public int getInventory(String productName) {
        return stocks.getOrDefault(productName, 0);
    }
}
