package ru.job4j.design.lsp.parking;

public class Truck implements Vehicle {
    private int size;

    public Truck(int size) {
        if (size > 1) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Размер грузового автомобиля должен быть больше 1");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

}
