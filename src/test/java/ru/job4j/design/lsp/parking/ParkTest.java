package ru.job4j.design.lsp.parking;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkTest {

    @Test
    public void whenCheckIsTrue() {
        Park park = new Park(10, 10);
        Vehicle car = new PassengerCar();
        assertThat(park.checkAvailableSpot(car, park.passengerCars), is(true));
    }

    @Test
    public void whenCheckIsFalse() {
        Park park = new Park(1, 1);
        Vehicle car = new PassengerCar();
        Vehicle car1 = new PassengerCar();
        park.add(car, park.passengerCars);
        assertThat(park.checkAvailableSpot(car1, park.passengerCars), is(false));
    }

    @Test
    public void whenAddTruck() {
        Truck truck = new Truck(3);
        Park park = new Park(10, 2);
        truck.occupie(park);
        assertThat(park.getPassengerCars()[0].getVehicle(), is(truck));
    }
}
