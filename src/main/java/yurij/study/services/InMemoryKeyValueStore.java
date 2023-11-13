package yurij.study.services;

import org.springframework.stereotype.Service;
import yurij.study.entity.StoreEntryEntity;

import java.util.*;

import static java.lang.System.currentTimeMillis;

/**
 * InMemory storage base implementation.
 */
@Service
public class InMemoryKeyValueStore {

    private final HashMap<String, StoreEntryEntity> store = new HashMap<>();

    /**
     * Add key/value pair to the storage.
     *
     * @param entity StoreEntryEntity input object
     */
    public synchronized StoreEntryEntity put(StoreEntryEntity entity) {
        store.put(entity.getKey(), entity);

        return entity;
    }

    /**
     * Get value by key.
     *
     * @param key String
     * @return String
     */
    public synchronized StoreEntryEntity get(String key) {

        return store.get(key);
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

        for (Map.Entry<String, StoreEntryEntity> entry : store.entrySet()) {
            StoreEntryEntity value = entry.getValue();

            dataList.add(value);
        }

        return dataList;
    }

    /**
     * Remove expired entries.
     */
    public synchronized void removeExpiredEntries() {
        for (Map.Entry<String, StoreEntryEntity> entry : store.entrySet()) {
            if (entry.getValue().getExpiryTimestamp() < currentTimeMillis()) {
                store.remove(entry.getKey());
            }
        }
    }
}
