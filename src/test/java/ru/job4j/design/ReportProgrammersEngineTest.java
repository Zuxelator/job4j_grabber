package ru.job4j.design;

import org.junit.Test;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.MemStore;
import ru.job4j.design.srp.ProgrammersReportEngine;
import ru.job4j.design.srp.Report;

import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ReportProgrammersEngineTest {
    @Test
    public void whenProgrammerGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ProgrammersReportEngine(store);
        String exp = "<!DOCTYPE html>\n"
                + "<html lang=\"ru\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Report</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <center>\n"
                + "        <p>Name; Hired; Fired; Salary;" + "</p>\n"
                + "        <p>Ivan;" + now.getTime()
                + ";" + now.getTime() + ";" + worker.getSalary() + ";" + "</p>\n"
                + "    </center>\n"
                + "</body> \n"
                + "</html>";
        assertThat(engine.generate(em -> true), is(exp));
    }

}