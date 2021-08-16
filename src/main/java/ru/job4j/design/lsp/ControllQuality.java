package ru.job4j.design.lsp;

import java.util.Map;

public class ControllQuality {
    private Storage storage;
    private Map<Food, Integer> tmpMap;

    public ControllQuality(Storage storage) {
        this.storage = storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void execute() {
        tmpMap = storage.execute(tmpMap);
    }

}
