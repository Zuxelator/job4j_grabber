package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Park implements Parking {
    public ParkingSpot[] passengerCars;
    public ParkingSpot[] trucks;
    private List<Vehicle> vehicles = new ArrayList<>();

    public Park(int amountOfPassengerSpots, int amountOfTruckSpots) {
        this.passengerCars = new ParkingSpot[amountOfPassengerSpots];
        this.trucks = new ParkingSpot[amountOfTruckSpots];

        for (int i = 0; i < amountOfPassengerSpots; i++) {
            passengerCars[i] = new ParkingSpot();
        }

        for (int i = 0; i < amountOfTruckSpots; i++) {
            trucks[i] = new ParkingSpot();
        }
    }

    @Override
    public ParkingSpot[] getPassengerCars() {
        return passengerCars;
    }

    @Override
    public ParkingSpot[] getTrucks() {
        return trucks;
    }

    public int getMaxFreeSpotsAtRow(ParkingSpot[] list) {
        int maxFreeSpotsAtRow = 0;
        int currentFreeSpotsAtrow = 0;
        for (int i = 0; i < list.length; i++) {
            if (!list[i].isOccupied()) {  //если свободно
                currentFreeSpotsAtrow++;
            } else {
                if (currentFreeSpotsAtrow > maxFreeSpotsAtRow) {
                    maxFreeSpotsAtRow = currentFreeSpotsAtrow;
                }
                currentFreeSpotsAtrow = 0;
            }
        }
        if (currentFreeSpotsAtrow > maxFreeSpotsAtRow) {
            maxFreeSpotsAtRow = currentFreeSpotsAtrow;
        }
        return maxFreeSpotsAtRow;
    }

    public Integer getStartOfMaxFreeSpotsRow(ParkingSpot[] list) {
        int sizeOfLongestRow = getMaxFreeSpotsAtRow(list);
        int start = 0;
        for (int i = 0; i < list.length; i++) {
            if (!list[i].isOccupied()) { //если свободно тогда начинаем считать
                start = i;
                boolean rsl = true;
                for (int j = i; j < sizeOfLongestRow + i; j++) {
                    if (list[j].isOccupied()) {
                        rsl = false;
                    }
                }
                if (rsl) {
                    return i;
                }
            }
        }
        return start;
    }

    @Override
    public boolean checkAvailableSpot(Vehicle vehicle, ParkingSpot[] list) {
        return vehicle.getSize() <= getMaxFreeSpotsAtRow(list);
    }

    @Override
    public boolean add(Vehicle vehicle, ParkingSpot[] list) {
        boolean rsl = false;
        if (checkAvailableSpot(vehicle, list)) {
            vehicles.add(vehicle);
            int start = getStartOfMaxFreeSpotsRow(list);
            for (int i = start; i < vehicle.getSize() + start; i++) {
                list[i].setIsOccupied(true);
                list[i].setVehicle(vehicle);
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void remove(Vehicle vehicle) {

    }
}
