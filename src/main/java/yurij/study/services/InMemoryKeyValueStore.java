package yurij.study.services;

import org.springframework.stereotype.Service;
import yurij.study.entity.StoreEntryEntity;

import java.util.*;

/**
 * InMemory storage base implementation.
 */
@Service
public class InMemoryKeyValueStore {

    private final HashMap<String, String> store = new HashMap<>();

    /**
     * Add key/value pair to the storage.
     *
     * @param entity StoreEntryEntity input object
     */
    public synchronized StoreEntryEntity put(StoreEntryEntity entity) {
        store.put(entity.getKey(), entity.getValue());

        return entity;
    }

    /**
     * Get value by key.
     *
     * @param key String
     * @return String
     */
    public synchronized StoreEntryEntity get(String key) {
        String value = store.get(key);

        return new StoreEntryEntity(key, value, expiryTimestamp);
    }

    /**
     * Remove value by key.
     *
     * @param key String
     */
    public synchronized void remove(String key) {
        store.remove(key);
    }

    /**
     * Get all cached objects.
     *
     * @return new ArrayList with data
     */
    public synchronized ArrayList<StoreEntryEntity> getAll() {

        ArrayList<StoreEntryEntity> dataList = new ArrayList<>();

        for (Map.Entry<String, String> entry : store.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            dataList.add(new StoreEntryEntity(key, value));
        }

        return dataList;
    }
}
