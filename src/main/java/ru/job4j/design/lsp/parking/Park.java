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
        boolean rsl = false;
        for (int i = 0; i < amountOfPassengerSpots; i++) {
            if (!spots[i].isOccupied()) {
                rsl = true;
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

    private void parkPassengerCar(Vehicle vehicle) {
        if (isFreeSpotsForPassengerCars()) {
            ParkingSpot freeSpot = spots[getFreeSpotForPassengerCar()];
            freeSpot.setVehicle(vehicle);
            freeSpot.setIsOccupied(true);
        } else {
            throw new IllegalArgumentException("На парковке нет мест для легковых машин!");
        }
    }

    private void parkTruck(Vehicle vehicle) {
        if (isFreeSpotsForTruck(vehicle)) {
            ParkingSpot freeSpot = null;
            int start = getStartOfMaxFreeSpotsRow(spots);
            for (int i = 0; i < vehicle.getSize(); i++) {
                freeSpot = spots[start--];
                freeSpot.setVehicle(vehicle);
                freeSpot.setIsOccupied(true);
            }
        } else {
            throw new IllegalArgumentException("На парковке нет мест для грузовика этого размера!");
        }
    }

    private boolean isFreeSpotsForTruck(Vehicle vehicle) {
        boolean rsl = false;
        int truckSize = vehicle.getSize();
        if (truckSize <= getMaxFreeSpotsAtRow(spots)) {
            rsl = true;
        }
        return rsl;
    }

    private int getMaxFreeSpotsAtRow(ParkingSpot[] list) {
        int maxFreeSpotsAtRow = 0;
        int currentFreeSpotsAtrow = 0;
        for (int i = 0; i < list.length; i++) {
            if (!list[i].isOccupied()) {
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
        int start = list.length - 1;
        for (int i = start; i > 0; i--) {
            if (!list[i].isOccupied()) {
                start = i;
                boolean rsl = true;
                for (int j = i; j > list.length - sizeOfLongestRow; j--) {
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
    public boolean add(Vehicle vehicle) {
        if (vehicle.getSize() == 1) {
            parkPassengerCar(vehicle);
        } else {
            parkTruck(vehicle);
        }
        return true;
    }

    @Override
    public Vehicle get(int number) {
        return spots[number].getVehicle();
    }
}
