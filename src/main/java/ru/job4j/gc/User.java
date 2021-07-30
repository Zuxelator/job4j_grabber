package ru.job4j.gc;

public class User {
    private static int count = 0;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", name='" + name + '\'' + '}';
    }

    @Override
    public void finalize() {
        System.out.printf("%d %s Deleted count = %d%n", id, name, ++count);
    }

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory);
        System.out.printf("Total: %d%n", totalMemory);
        System.out.printf("Max: %d%n", maxMemory);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10000; i++) {
            new User(i, "Петя" + i);
        }
        //System.gc();
        info();
    }
}
