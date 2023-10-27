package yurij.study.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * InMemory storage base implementation.
 */
@Service
public class InMemoryKeyValueStore {

    private final HashMap<String, String> store = new HashMap<String, String>();

    /**
     * Add key/value pair to the storage.
     * @param key String
     * @param value String
     */
    public void put(String key, String value) {
        store.put(key, value);
    }

    /**
     * Get value by key.
     * @param key String
     * @return String
     */
    public String get(String key) {
        return store.get(key);
    }

    /**
     * Remove value by key
     * @param key String
     */
    public void remove(String key) {
        store.remove(key);
    }
}
