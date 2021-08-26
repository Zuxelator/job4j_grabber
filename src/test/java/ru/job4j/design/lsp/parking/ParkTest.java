package ru.job4j.design.lsp.parking;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenNotEnoughSpotForCar() {
        Parking park = new Park(1, 1);
        PassengerCar car = new PassengerCar();
        PassengerCar car1 = new PassengerCar();
        park.add(car);
        park.add(car1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotEnoughSpotForTruck() {
        Parking park = new Park(1, 1);
        Truck truck = new Truck(3);
        park.add(truck);
    }

    @Test
    public void whenAddTruck() {
        Truck truck = new Truck(3);
        Park park = new Park(10, 2);
        park.add(truck);
        assertThat(park.getSpots()[11].getVehicle(), is(truck));
    }

    @Test
    public void whenAddCar() {
        PassengerCar car = new PassengerCar();
        Park park = new Park(10, 2);
        park.add(car);
        assertThat(park.getSpots()[0].getVehicle(), is(car));
    }
}
