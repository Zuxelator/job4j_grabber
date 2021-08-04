package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class Task {
    public static void main(String[] args) throws InterruptedException {
        User user = new User(1, "Юзер");
        WeakReference<User> weak = new WeakReference<>(user);
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
