package ru.job4j.design.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ControllQualityTest {

    @Test
    public void whenAddThenTrash() {
        LocalDate create0 = LocalDate.of(2021, 8, 24);
        LocalDate expired0 = LocalDate.of(2021, 8, 27);
        Milk milkToTrash = new Milk("Молоко", expired0, create0, 100, 0);
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        ControllQuality controllQuality = new ControllQuality(List.of(shop, trash, warehouse));
        controllQuality.distribute(milkToTrash);
        assertThat(trash.get(milkToTrash), is(1));
    }

    @Test
    public void whenAddThenWarehouse() {
        LocalDate create100 = LocalDate.of(2021, 8, 28);
        LocalDate expired100 = LocalDate.of(2021, 8, 30);
        Milk milkToWarehouse = new Milk("Молоко", expired100, create100, 100, 0);
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        ControllQuality controllQuality = new ControllQuality(List.of(shop, trash, warehouse));
        controllQuality.distribute(milkToWarehouse);
        assertThat(warehouse.get(milkToWarehouse), is(1));
    }

    @Test
    public void whenAddThenShop() {
        LocalDate create50 = LocalDate.of(2021, 8, 26);
        LocalDate expired50 = LocalDate.of(2021, 8, 30);
        Milk milkToShop = new Milk("Молоко", expired50, create50, 100, 0);
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        ControllQuality controllQuality = new ControllQuality(List.of(shop, trash, warehouse));
        controllQuality.distribute(milkToShop);
        assertThat(shop.get(milkToShop), is(1));
    }

    @Test
    public void whenAddThenShopWithDiscount() {
        LocalDate create10 = LocalDate.of(2021, 8, 19);
        LocalDate expired10 = LocalDate.of(2021, 8, 29);
        Milk milkToShop = new Milk("Молоко", expired10, create10, 100, 0);
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        ControllQuality controllQuality = new ControllQuality(List.of(shop, trash, warehouse));
        controllQuality.distribute(milkToShop);
        assertThat(shop.get(milkToShop), is(1));
        assertThat(milkToShop.getPrice(), is(80.0));
    }

    @Test
    public void whenResortThenMilkToShopBreadToWarehouse() {
        LocalDate create10 = LocalDate.of(2021, 8, 19);
        LocalDate expired10 = LocalDate.of(2021, 8, 29);
        LocalDate create100 = LocalDate.of(2021, 8, 28);
        LocalDate expired100 = LocalDate.of(2021, 8, 30);
        Milk milk = new Milk("Молоко", expired10, create10, 100, 0);
        Bread bread = new Bread("Хлеб", expired100, create100, 100, 0);
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        trash.add(milk);
        trash.add(bread);
        ControllQuality controllQuality = new ControllQuality(List.of(shop, trash, warehouse));
        controllQuality.resort();
        assertThat(trash.getMap().size(), is(0));
        assertThat(shop.get(milk), is(1));
        assertThat(warehouse.get(bread), is(1));
    }
}