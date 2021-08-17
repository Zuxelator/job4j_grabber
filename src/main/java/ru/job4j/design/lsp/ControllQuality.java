package ru.job4j.design.lsp;

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
}
