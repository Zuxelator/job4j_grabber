package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Shop implements Storage {
    private Map<Food, Integer> map = new HashMap<>();

    public Map<Food, Integer> getMap() {
        return map;
    }

    public void add(Food food) {
        if (map.get(food) == null) {
            map.put(food, 1);
        } else {
            map.put(food, map.get(food) + 1);
        }
    }

    public Integer get(Food food) {
        return map.get(food);
    }

    @Override
    public double getRemainingShelfLife(Food food) {
        LocalDate createDate = food.getCreateDate();
        LocalDate expiredDate = food.getExpiryDate();
        LocalDate now = LocalDate.now();
        long fullShelfLife = ChronoUnit.DAYS.between(createDate, expiredDate);
        return ChronoUnit.DAYS.between(now, expiredDate) / (double) fullShelfLife * 100;
    }

    @Override
    public boolean accept(Food food) {
        double shelfLife = getRemainingShelfLife(food);
        if (shelfLife > 0 && shelfLife < 25) {
            food.setDiscount(20);
        }
        return shelfLife >= 25 && shelfLife < 75;
    }

    @Override
    public String toString() {
        return "Shop{" + "map=" + map + '}';
    }
}
