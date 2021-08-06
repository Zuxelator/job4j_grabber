package ru.job4j.gc;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

public class Task {
    public static void main(String[] args) throws InterruptedException {
        User user = new User(1, "Юзер");
        Reference<User> weak = new SoftReference<>(user);
        user = null;
        for (int i = 0; i < 1000; i++) {
            new User(i, String.valueOf(System.currentTimeMillis()));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        User weakUser = weak.get();
        if (weakUser != null) {
            System.out.println("weakUser: " + weakUser);
        } else {
            System.out.println("weakUser: Null");
        }

    }
}
