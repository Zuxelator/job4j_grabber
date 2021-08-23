package ru.job4j.design.lsp.parking;

public class ParkingSpot {
    private Vehicle vehicle;
    private boolean isOccupied;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
