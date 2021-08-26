package ru.job4j.design.lsp.parking;

public interface Parking {
    boolean add(Vehicle vehicle);
    Vehicle get(int number);
}
