package ru.job4j.design.lsp.parking;

public class Truck implements Vehicle {
    private int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void occupie(Parking parking) {
         if (!parking.add(this, parking.getTrucks())) {
             parking.add(this, parking.getPassengerCars());
         }
    }
}
