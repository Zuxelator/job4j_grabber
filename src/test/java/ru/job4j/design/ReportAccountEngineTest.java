package ru.job4j.design;

import org.junit.Test;
import ru.job4j.design.srp.AccountReportEngine;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.MemStore;
import ru.job4j.design.srp.Report;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportAccountEngineTest {

    @Test
    public void whenAccountGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new AccountReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append((worker.getSalary()) * 74 + " Rub").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
