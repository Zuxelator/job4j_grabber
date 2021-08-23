package ru.job4j.design.lsp.parking;

public class Test {
    public static void main(String[] args) {
        Park park = new Park(10, 10);
        Truck truck = new Truck(4);
        Truck truck2 = new Truck(4);
        Truck truck3 = new Truck(4);
        truck.occupie(park);
        truck2.occupie(park);
        PassengerCar car = new PassengerCar();
        car.occupie(park);
        truck3.occupie(park);
    }
}
