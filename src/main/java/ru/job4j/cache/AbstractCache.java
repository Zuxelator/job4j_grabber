package ru.job4j.cache;

import ru.job4j.grabber.Parse;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<>(value);
        cache.put(key, softValue);
    }

    public V get(K key) {
        SoftReference<V> softReference = cache.get(key);
        V value;
        if (softReference == null) {
            value = load(key);
            cache.put(key, new SoftReference<>(value));
        } else {
            value = softReference.get();
            if (value == null) {
                value = load(key);
                cache.put(key, new SoftReference<>(value));
            }
        }
        return value;
    }

    protected abstract V load(K key);
}