package ru.job4j.design.lsp.parking;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParkTest {

    @Test
    public void whenCheckIsTrue() {
        Park park = new Park(10);
        Vehicle car = new PassengerCar();
        assertThat(park.checkAvailableSpot(car), is(true));
    }

    @Test
    public void whenCheckIsFalse() {
        Park park = new Park(1);
        Vehicle car = new PassengerCar();
        Vehicle car1 = new PassengerCar();
        park.add(car);
        assertThat(park.checkAvailableSpot(car1), is(false));
    }

    @Test
    public void whenCheckIsFalse() {
        Park park = new Park(1);
        Vehicle car = new PassengerCar();
        Vehicle car1 = new PassengerCar();
        park.add(car);
        assertThat(park.checkAvailableSpot(car1), is(false));
    }

    @Test
    public void whenAddTruck() {
        CargoTruck cg = new CargoTruck(3);
        ParkingService ps = new ParkingService(new Park(3), cg);
        assertThat(ps.getSpot(0), is(cg));
    }

}