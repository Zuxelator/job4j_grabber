package ru.job4j.design.lsp.parking;

public class Test {
    public static void main(String[] args) {
        Parking park = new Park(5, 1);

        Truck truck = new Truck(4);
        Truck truck1 = new Truck(4);
        PassengerCar car = new PassengerCar();
        //PassengerCar car1 = new PassengerCar();
        park.add(car);
        //park.add(car1);
        park.add(truck);
        park.add(truck1);
        System.out.println();
    }
}
