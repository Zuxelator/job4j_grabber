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
        Truck truck = new Truck(3);
        Truck truck1 = new Truck(3);
        assertThat(park.add(truck1), is (true));
        assertThat(park.add(truck), is(false));
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

    @Test
    public void whenAllPassengersCarSpotsOccupied() {
        Park park = new Park(1, 1);
        PassengerCar car = new PassengerCar();
        PassengerCar car1 = new PassengerCar();
        park.add(car);
        assertThat(park.add(car1), is(false));
        assertThat(park.getSpots()[1].isOccupied(), is(false));
    }

    @Test
    public void whenAllTruckSpotsOccupied() {
        Park park = new Park(4, 1);
        PassengerCar car = new PassengerCar();
        Truck truck = new Truck(3);
        Truck truck1 = new Truck(3);
        park.add(car);
        park.add(truck);
        assertThat(park.add(truck1), is(true));
    }
}
