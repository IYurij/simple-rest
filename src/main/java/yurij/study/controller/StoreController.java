package yurij.study.controller;

import org.springframework.web.bind.annotation.*;
import yurij.study.entity.StoreEntry;
import yurij.study.services.InMemoryKeyValueStore;

/**
 * API InMemoryStore controller class
 */
@RestController
@RequestMapping("store")
public class StoreController {
    private final InMemoryKeyValueStore inMemoryKeyValueStore;

    public StoreController(InMemoryKeyValueStore inMemoryKeyValueStore) {
        this.inMemoryKeyValueStore = inMemoryKeyValueStore;
    }

    /**
     * Get value by key.
     * @param key String
     * @return StoryEntry object
     */
    @GetMapping("{key}")
    public StoreEntry getOne(@PathVariable String key) {
        return inMemoryKeyValueStore.get(key);
    }

    /**
     * Add new key/value pair
     * @param entry input object
     * @return StoreEntry object
     */
    @PostMapping
    public StoreEntry putOne(@RequestBody StoreEntry entry) {
        return inMemoryKeyValueStore.put(entry.getKey(), entry.getValue());
    }

    /**
     * Delete Store data by key.
     * @param key String
     */
    @DeleteMapping("{key}")
    public void remove(@PathVariable String key) {
        inMemoryKeyValueStore.remove(key);
    }
}
