package ru.job4j.kiss;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    @Test
    public void minTest() {
        MaxMin maxMin = new MaxMin();
        List<Model> list = Arrays.asList(new Model(1, "1"),
                new Model(2, "2"),
                new Model(3, "3"));
        Comparator<Model> comparator = new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o2.getId() - o1.getId();
            }
        };
        assertThat(new Model(3, "3"), is(maxMin.max(list, comparator)));
    }

    @Test
    public void maxTest() {
        MaxMin maxMin = new MaxMin();
        List<Model> list = Arrays.asList(new Model(1, "1"),
                new Model(2, "2"),
                new Model(3, "3"));
        Comparator<Model> comparator = new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return o2.getId() - o1.getId();
            }
        };
        assertThat(new Model(1, "1"), is(maxMin.min(list, comparator)));
    }
}