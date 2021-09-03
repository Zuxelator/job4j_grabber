package ru.job4j.design.lsp.parking;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParkTest {

    @Test
    public void whenNotEnoughSpotForCar() {
        Parking park = new Park(1, 1);
        PassengerCar car = new PassengerCar();
        PassengerCar car1 = new PassengerCar();
        park.add(car);
        assertFalse(park.add(car1));
    }

    @Test
    public void whenNotEnoughSpotForTruck() {
        Parking park = new Park(1, 1);
        Vehicle truck = new Truck(3);
        Vehicle truck1 = new Truck(3);
        assertTrue(park.add(truck1));
        assertFalse(park.add(truck));
    }

    @Test
    public void whenAddTruck() {
        Vehicle truck = new Truck(3);
        Parking park = new Park(10, 2);
        park.add(truck);
        assertThat(park.get(11), is(truck));
    }

    @Test
    public void whenAddCar() {
        Vehicle car = new PassengerCar();
        Parking park = new Park(10, 2);
        park.add(car);
        assertThat(park.get(0), is(car));
    }

    @Test
    public void whenAllPassengersCarSpotsOccupied() {
        Parking park = new Park(1, 1);
        Vehicle car = new PassengerCar();
        Vehicle car1 = new PassengerCar();
        park.add(car);
        assertFalse(park.add(car1));
    }

    @Test
    public void whenAllTruckSpotsOccupied() {
        Parking park = new Park(4, 1);
        Vehicle car = new PassengerCar();
        Vehicle truck = new Truck(3);
        Vehicle truck1 = new Truck(3);
        park.add(car);
        park.add(truck);
        assertTrue(park.add(truck1));
    }

    @Test
    public void whenAdd2Car() {
        Parking park = new Park(2, 2);
        Vehicle car = new PassengerCar();
        Vehicle car1 = new PassengerCar();
        park.add(car);
        assertTrue(park.add(car1));
    }
}
