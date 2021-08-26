package ru.job4j.design.lsp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllQuality {
    private Storage storage;
    private List<Storage> storageList;

    public ControllQuality(List<Storage> storageList) {
        this.storageList = storageList;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void distribute(Food food) {
        for (Storage storage : storageList) {
            if (storage.accept(food)) {
                storage.add(food);
            }
        }
    }

    private Map<Food, Integer> getAllFoods() {
        Map<Food, Integer> foods = new HashMap<>();
        for (Storage storage : storageList) {
            for (Map.Entry<Food, Integer> entry : storage.getMap().entrySet()) {
                foods.merge(entry.getKey(), 1, (a, b) -> b + 1);
            }
        }
        return foods;
    }

    private void clearAllStorages() {
        for (Storage storage : storageList) {
            storage.getMap().clear();
        }
    }

    public void resort() {
        Map<Food, Integer> foods = getAllFoods();
        clearAllStorages();
        for (Map.Entry<Food, Integer> entry : foods.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                distribute(entry.getKey());
            }
        }
    }
}
