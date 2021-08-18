package ru.job4j.design.lsp;

import java.util.Map;

public interface Storage {
    void add(Food food);
    Integer get(Food food);
    boolean accept(Food food);
    Map<Food, Integer> getMap();
    double getRemainingShelfLife(Food food);
}
