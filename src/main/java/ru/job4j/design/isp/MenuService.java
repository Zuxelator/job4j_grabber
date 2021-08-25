package ru.job4j.design.isp;

public class MenuService {
    public static void main(String[] args) {
        TaskMenu menu = new TaskMenu();
        menu.addRootElement("Задача");
        menu.addRootElement("Задача");
        menu.addRootElement("Задача");
        menu.addSubParagraph("Задача 2.");
        menu.addSubParagraph("Задача 2.");
        menu.addSubParagraph("Задача 1.");
        menu.addSubParagraph("Задача 2.2.");
        menu.addSubParagraph("Задача 2.2.1.");
        menu.addSubParagraph("Задача 1.1.");
        menu.addSubParagraph("Задача 1.1.");
        menu.addSubParagraph("Задача 1.");
        menu.showMenu();
    }
}
