package yurij.study.services;

import org.springframework.stereotype.Service;
import yurij.study.entity.StoreEntryEntity;

import javax.annotation.Nullable;
import java.util.HashMap;

/**
 * InMemory storage base implementation.
 */
@Service
public class InMemoryKeyValueStore {

    private final HashMap<String, String> store = new HashMap<>();

    /**
     * Add key/value pair to the storage.
     * @param entity StoreEntryEntity input object
     */
    public StoreEntryEntity put(StoreEntryEntity entity) {
        store.put(entity.getKey(), entity.getValue());

        return entity;
    }

    /**
     * Get value by key.
     * @param key String
     * @return String
     */
    public StoreEntryEntity get(String key) {
        String value = store.get(key);

        return new StoreEntryEntity(key, value);
    }

    /**
     * Remove value by key
     * @param key String
     */
    public void remove(String key) {
        store.remove(key);
    }
}
