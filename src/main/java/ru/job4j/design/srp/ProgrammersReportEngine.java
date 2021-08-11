package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ProgrammersReportEngine extends ReportEngine {
    public ProgrammersReportEngine(Store store) {
        super(store);
    }

    public String generate(Predicate<Employee> filter) {

        String html1 = "<!DOCTYPE html>\n"
                + "<html lang=\"ru\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Report</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <center>\n";
        StringBuilder text = new StringBuilder(html1);
        text.append("        <p>Name; Hired; Fired; Salary;</p>\n");
        for (Employee employee : this.getStore().findBy(filter)) {
            text.append("        <p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>\n");
        }
        text.append("    </center>\n"
                + "</body> \n"
                + "</html>");
        return text.toString();
    }

}
