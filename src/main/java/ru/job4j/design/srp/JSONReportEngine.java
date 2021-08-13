package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.function.Predicate;

public class JSONReportEngine implements Report {
    private Store store;

    public JSONReportEngine(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = this.getStore().findBy(filter);
        var lib = new GsonBuilder().create();
        return lib.toJson(list);
    }
}
