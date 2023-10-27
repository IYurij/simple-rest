package yurij.study.services;

import org.springframework.stereotype.Service;
import yurij.study.entity.StoreEntry;

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
    public StoreEntry put(String key, String value) {
        store.put(key, value);

        return new StoreEntry(key, value);
    }

    /**
     * Get value by key.
     * @param key String
     * @return String
     */
    public StoreEntry get(String key) {
        String value = store.get(key);

        return new StoreEntry(key, value);
    }

    /**
     * Remove value by key
     * @param key String
     */
    public void remove(String key) {
        store.remove(key);
    }
}
