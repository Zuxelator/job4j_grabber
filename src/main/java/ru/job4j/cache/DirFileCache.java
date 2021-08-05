package ru.job4j.cache;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public String getCachingDir() {
        return cachingDir;
    }

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String cachingDir) {
        String rsl = "";
        try {
            rsl = Files.readString(Path.of(cachingDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

}