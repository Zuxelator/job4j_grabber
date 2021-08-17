package ru.job4j.design.lsp;

import java.util.Map;

public interface Storage {
    public void add(Food food);
    public Integer get(Food food);
    boolean accept(Food food);
    public Map<Food, Integer> getMap();
}
