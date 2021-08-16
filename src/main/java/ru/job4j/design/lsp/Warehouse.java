package ru.job4j.design.lsp;

import java.util.HashMap;
import java.util.Map;

public class Warehouse implements Storage {
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
    public Map<Food, Integer> execute(Map<Food, Integer> tmp) {
        Map<Food, Integer> tmpMap = new HashMap<>(map);
        tmpMap.keySet().removeIf(x -> x.getRemainingShelfLife() >= 75);
        map.keySet().removeIf(x -> x.getRemainingShelfLife() < 75);
        return tmpMap;
    }

    @Override
    public String toString() {
        return "Warehouse{" + "map=" + map + '}';
    }
}
