package ru.job4j.design.ocp;

import com.mchange.v2.codegen.bean.Property;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Examples {
    //пример 1
    //был класс Car, нужно изменить метод drive
    //если это сделать то нарушится принцип OCP
    class Car {
        public void drive() {
            //System.out.println("Car is driving");
            System.out.println("The car transports goods");
        }
    }
    // Чтобы не нарушать принцип OCP нужно изначально создать интерфейс ICar с методом drive и реализовывать его
    // в классах в зависимости от требований к методу.

    //пример 2
    //был класс Printer, который выводил в консоль переданную строку
    //если внести изменения, то нарушится принцип OCP
    class Printer {
        public void print(String str) {
            //System.out.println(str);
            System.out.println("Строка: " + str);
        }
    }
    // Чтобы не нарушать принцип OCP можно создать класс наследник и у него переопределить метод print

    //пример 3
    //Потребовалось получить подключение к другой БД
    //Если изменить метод getDB1Connection, то не сможем подключиться к старой базе.
    //этот метод изменять нельзя
    class GetConnection {
        public Connection getDB1Connection(Properties cfg) throws Exception {
            Class.forName(cfg.getProperty("jdbc.driver"));
            return DriverManager.getConnection(cfg.getProperty("url"),
                    cfg.getProperty("user"),
                    cfg.getProperty("password"));
        }
    }
}
