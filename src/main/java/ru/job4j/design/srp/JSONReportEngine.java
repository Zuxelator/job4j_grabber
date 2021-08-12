package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.function.Predicate;

public class JSONReportEngine extends ReportEngine {
    public JSONReportEngine(Store store) {
        super(store);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = this.getStore().findBy(em -> true);
        var lib = new GsonBuilder().create();
        return lib.toJson(list);
    }
}
