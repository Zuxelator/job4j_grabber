package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.List;

public class Element {
    private String name;
    private int deep;
    private List<Element> nodes = new ArrayList<>();

    public Element(String name, int deep) {
        this.name = name;
        this.deep = deep;
    }

    public Element(String name, List<Element> list) {
        this.name = name;
        this.nodes = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public List<Element> getNodes() {
        return nodes;
    }

    public void setNodes(List<Element> nodes) {
        this.nodes = nodes;
    }
}
