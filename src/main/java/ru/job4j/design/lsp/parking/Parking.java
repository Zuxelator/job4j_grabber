package ru.job4j.design.lsp.parking;

public interface Parking {
    boolean checkAvailableSpot(Vehicle vehicle, ParkingSpot[] list);
    boolean add(Vehicle vehicle, ParkingSpot[] list);
    void remove(Vehicle vehicle);
    ParkingSpot[] getTrucks();
    ParkingSpot[] getPassengerCars();
}
