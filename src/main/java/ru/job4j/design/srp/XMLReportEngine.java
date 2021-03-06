package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XMLReportEngine implements Report {

    private Store store;

    public XMLReportEngine(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = this.getStore().findBy(filter);
        Employees employees = new Employees();
        employees.setList(list);
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(employees, writer);
                xml = writer.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
