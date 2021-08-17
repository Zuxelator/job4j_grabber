package ru.job4j.design.lsp;

import java.util.HashMap;
import java.util.Map;

public class Trash implements Storage {
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
    public boolean accept(Food food) {
        return food.getRemainingShelfLife() <= 0;
    }

    @Override
    public String toString() {
        return "Trash{" + "map=" + map + '}';
    }
}
