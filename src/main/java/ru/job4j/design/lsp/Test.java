package ru.job4j.design.lsp;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        LocalDate create75 = LocalDate.of(2021, 8, 14);
        LocalDate expired75 = LocalDate.of(2021, 8, 28);
        LocalDate create50 = LocalDate.of(2021, 8, 14);
        LocalDate expired50 = LocalDate.of(2021, 8, 18);
        LocalDate create10 = LocalDate.of(2021, 8, 10);
        LocalDate expired10 = LocalDate.of(2021, 8, 17);
        Milk milk75 = new Milk("Молоко75", expired75, create75, 22, 0);
        Milk milk50 = new Milk("Молоко50", expired50, create50, 22, 0);
        Milk milk10 = new Milk("Молоко10", expired10, create10, 22, 0);
        Milk milk101 = new Milk("Молоко10", expired10, create10, 22, 0);
        Bread bread10 = new Bread("Хлеб10", expired10, create10, 5, 0);
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        shop.add(milk101);
        ControllQuality controllQuality = new ControllQuality(List.of(warehouse, shop, trash));
        controllQuality.distribute(milk75);
        controllQuality.distribute(milk50);
        controllQuality.distribute(milk10);
        controllQuality.distribute(bread10);
        controllQuality.resort();
        System.out.println();
    }
}
