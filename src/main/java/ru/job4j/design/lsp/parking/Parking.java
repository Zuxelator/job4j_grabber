package ru.job4j.design.lsp.parking;

public interface Parking {
    boolean checkAvailableSpot(Vehicle vehicle);
    void add(Vehicle vehicle);
    void remove(Vehicle vehicle);
}
