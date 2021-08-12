package ru.job4j.design.srp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
public class Employees {
    @XmlElement(name = "employee")
    private List<Employee> list;

    public Employees() {
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }
}
