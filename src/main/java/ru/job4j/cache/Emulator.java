package ru.job4j.cache;

public class Emulator {

    private DirFileCache dirFileCache;

    public void setCacheDirectory(String directory) {
        dirFileCache = new DirFileCache(directory);
    }

    public void loadCache() {
        dirFileCache.load(dirFileCache.getCachingDir());
    }

    public String getCache() {
        return dirFileCache.get(dirFileCache.getCachingDir());
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.setCacheDirectory("123.txt");
        emulator.loadCache();
        System.out.println(emulator.getCache());
    }
}
