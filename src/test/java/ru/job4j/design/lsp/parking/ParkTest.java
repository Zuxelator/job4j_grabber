package ru.job4j.design.lsp.parking;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkTest {

    @Test
    public void whenNotEnoughSpotForCar() {
        Parking park = new Park(1, 1);
        PassengerCar car = new PassengerCar();
        PassengerCar car1 = new PassengerCar();
        park.add(car);
        assertThat(park.add(car1), is(false));
    }

    @Test
    public void whenNotEnoughSpotForTruck() {
        Parking park = new Park(1, 1);
        Vehicle truck = new Truck(3);
        Vehicle truck1 = new Truck(3);
        assertThat(park.add(truck1), is(true));
        assertThat(park.add(truck), is(false));
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
        assertThat(park.add(car1), is(false));
    }

    @Test
    public void whenAllTruckSpotsOccupied() {
        Parking park = new Park(4, 1);
        Vehicle car = new PassengerCar();
        Vehicle truck = new Truck(3);
        Vehicle truck1 = new Truck(3);
        park.add(car);
        park.add(truck);
        assertThat(park.add(truck1), is(true));
    }
}
