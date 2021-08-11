package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountReportEngine extends ReportEngine {

    public AccountReportEngine(Store store) {
        super(store);
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee employee : this.getStore().findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * 74 + " Rub").append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
