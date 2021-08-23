package ru.job4j.design.lsp.parking;

public class PassengerCar implements Vehicle {
    private int size = 1;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void occupie(Parking parking) {
        parking.add(this, parking.getPassengerCars());
    }
}
