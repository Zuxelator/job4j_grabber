package ru.job4j.cache;

import org.w3c.dom.ls.LSOutput;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
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
        if (softReference == null || softReference.get() == null) {
            value = load(key);
            cache.put(key, new SoftReference<>(value));
        } else {
            value = cache.get(key).get();
        }
        return value;
    }

    protected abstract V load(K key);
}