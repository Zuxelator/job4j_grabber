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

    private void initSpots() {
        for (int i = 0; i < spots.length; i++) {
            spots[i] = new ParkingSpot();
        }
    }

    private boolean isFreeSpotsForPassengerCars() {
        for (int i = 0; i < amountOfPassengerSpots; i++) {
            if (!spots[i].isOccupied()) {
                return true;
            }
        }
        return false;
    }

    private int getFreeSpotForPassengerCar() {
        for (int i = 0; i < amountOfPassengerSpots; i++) {
            if (!spots[i].isOccupied()) {
                return i;
            }
        }
        return 0;
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
        } else if (isFreeSpotsForTruckOnPassengersSpots(vehicle)) {
            int start = getStartOfMaxFreeSpotsRow();
            rsl = true;
            for (int i = start; i <= vehicle.getSize(); i++) {
                spots[i].setIsOccupied(true);
                spots[i].setVehicle(vehicle);
            }
        }
        return rsl;
    }

    private int getStartOfMaxFreeSpotsRow() {
        int rsl = 0;
        int sizeOfLongestRow = getMaxFreeSpotsAtRowInPassengersSpots();
        int count = 0;
        int amountOfspots = spots.length - amountOfTruckSpots;
        for (int i = 0; i < amountOfspots - 1; i++) {
            if (!spots[i].isOccupied()) {
                count++;
                for (int j = i + 1; j < amountOfspots; j++) {
                    if (!spots[j].isOccupied()) {
                        count++;
                    }
                }
                if (count == sizeOfLongestRow) {
                    rsl = i;
                }
            }
        }
        return rsl;
    }

    private boolean isFreeSpotsForTruckOnPassengersSpots(Vehicle vehicle) {
        return vehicle.getSize() <= getMaxFreeSpotsAtRowInPassengersSpots();
    }

    private int getMaxFreeSpotsAtRowInPassengersSpots() {
        int maxFreeSpotsAtRow = 0;
        int currentFreeSpotsAtrow = 0;
        for (int i = 0; i < spots.length - amountOfTruckSpots; i++) {
            if (!spots[i].isOccupied()) {
                currentFreeSpotsAtrow++;
            } else {
                if (currentFreeSpotsAtrow > maxFreeSpotsAtRow) {
                    maxFreeSpotsAtRow = currentFreeSpotsAtrow;
                    currentFreeSpotsAtrow = 0;
                }
            }
        }
        if (currentFreeSpotsAtrow > maxFreeSpotsAtRow) {
            maxFreeSpotsAtRow = currentFreeSpotsAtrow;
        }
        return maxFreeSpotsAtRow;
    }

    private boolean isFreeSpotsForTruck() {
        boolean rsl = false;
        for (int i = spots.length - 1; i >= spots.length - amountOfTruckSpots; i--) {
            if (!spots[i].isOccupied()) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }
}
