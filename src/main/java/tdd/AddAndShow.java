package tdd;

import java.util.ArrayList;
import java.util.List;

public class AddAndShow {
    private List<String> list = new ArrayList<>();

    public void add(String str) {
        list.add(str);
    }

    public void show() {
        System.out.println(list);
    }

}
