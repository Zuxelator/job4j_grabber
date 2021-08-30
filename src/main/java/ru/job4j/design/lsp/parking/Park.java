package ru.job4j.design.lsp.parking;

public class Park implements Parking {
    public ParkingSpot[] spots;
    public int amountOfPassengerSpots;
    public int amountOfTruckSpots;

    public Park(int amountOfPassengerSpots, int amountOfTruckSpots) {
        this.amountOfPassengerSpots = amountOfPassengerSpots;
        this.amountOfTruckSpots = amountOfTruckSpots;
        this.spots = new ParkingSpot[amountOfPassengerSpots + amountOfTruckSpots];
        initSpots();
    }

    public ParkingSpot[] getSpots() {
        return spots;
    }

    public void setSpots(ParkingSpot[] spots) {
        this.spots = spots;
    }

    private void initSpots() {
        for (int i = 0; i < spots.length; i++) {
            spots[i] = new ParkingSpot();
        }
    }

    private boolean isFreeSpotsForPassengerCars() {
        boolean rsl = true;
        for (int i = 0; i < amountOfPassengerSpots; i++) {
            if (spots[i].isOccupied()) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    private int getFreeSpotForPassengerCar() {
        int rsl = 0;
        for (int i = 0; i < amountOfPassengerSpots; i++) {
            if (!spots[i].isOccupied()) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    private boolean parkPassengerCar(Vehicle vehicle) {
        boolean rsl = false;
        if (isFreeSpotsForPassengerCars()) {
            ParkingSpot freeSpot = spots[getFreeSpotForPassengerCar()];
            freeSpot.setVehicle(vehicle);
            freeSpot.setIsOccupied(true);
            rsl = true;
        }
        return rsl;
    }

    private boolean parkTruck(Vehicle vehicle) {
        boolean rsl = false;
        if (isFreeSpotsForTruck()) {
            for (int i = spots.length - 1; i >= spots.length - amountOfTruckSpots; i--) {
                if (!spots[i].isOccupied()) {
                    rsl = true;
                    spots[i].setIsOccupied(true);
                    spots[i].setVehicle(vehicle);
                    break;
                }
            }
        }
        return rsl;
    }

    public boolean isFreeSpotsForTruck() {
        boolean rsl = false;
        for (int i = spots.length - 1; i >= spots.length - amountOfTruckSpots; i--) {
            if (!spots[i].isOccupied()) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        boolean rsl = false;
        if (vehicle.getSize() == 1) {
            rsl = parkPassengerCar(vehicle);
        } else {
            rsl = parkTruck(vehicle);
        }
        return rsl;
    }

    @Override
    public Vehicle get(int number) {
        return spots[number].getVehicle();
    }
}
