package ru.job4j.design;

import org.junit.Test;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.HRReportEngine;
import ru.job4j.design.srp.MemStore;
import ru.job4j.design.srp.Report;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportHREngineTest {

    @Test
    public void whenAccountGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vasia", now, now, 500);
        Employee worker3 = new Employee("Petr", now, now, 250);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append((worker2.getSalary())).append(";")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append((worker3.getSalary())).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append((worker1.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
