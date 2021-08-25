package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TaskMenu implements AddRootElement, AddSubParagraph, Showable  {
    List<Element> menu = new ArrayList<>();

    @Override
    public void addRootElement(String name) {
        Element node = new Element(name + " " + (menu.size() + 1) + ".", 0);
        menu.add(node);
    }

    @Override
    public void addSubParagraph(String nameOfNode) {
        Element parent = search(nameOfNode);
        String parentName = parent.getName();
        String newName = parentName + (parent.getNodes().size() + 1) + ".";
        parent.getNodes().add(new Element(newName, parent.getDeep() + 1));
    }

    @Override
    public void showMenu() {
        String line = "----";
        for (Element node : menu) {
            Queue<Element> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                Element element = queue.remove();
                System.out.println(line.repeat(element.getDeep()) + " " + element.getName());
                queue.addAll(element.getNodes());
            }
        }
    }

    public void select(String menuParagraph, Action action) {
        Element element = search(menuParagraph);
        action.doSomething(element);
    }

    private Element search(String nameOfNode) {
        Element rsl = null;
        for (Element node : menu) {
            Queue<Element> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                Element element = queue.remove();
                if (element.getName().equals(nameOfNode)) {
                    rsl = element;
                    break;
                }
                if (element.getNodes() != null) {
                    queue.addAll(element.getNodes());
                }
            }
        }
        if (rsl == null) {
            throw new IllegalArgumentException("Такого пункта меню не существует!");
        }
        return rsl;
    }
}
