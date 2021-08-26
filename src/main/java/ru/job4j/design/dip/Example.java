package ru.job4j.design.dip;

public class Example {
    //пример 1. Класс Car зависит от конкретного класса PetrolEngine, а не от интерфейса
    class Car {
        private PetrolEngine engine;

    }

    class PetrolEngine {

    }

    //пример 2 Метод generateReport возвращает конкретный класс, а не ссылку на интерфейс
    class ReportService {

        private Report generateReport(String nameOfReport) {
            return new Report();
        }

    }

    class Report {

    }

    //пример 3 Метод eat принимает в аргументы объект класса Food, а не переменную интерфейса
    class Dog {
        void eat(Food food) {

        }
    }

    class Food {

    }
}
