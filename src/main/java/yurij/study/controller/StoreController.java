package yurij.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yurij.study.dto.StoreEntryDTO;
import yurij.study.dto.mapping.StoreEntryMapping;
import yurij.study.entity.StoreEntryEntity;
import yurij.study.services.InMemoryKeyValueStore;

/**
 * API InMemoryStore controller class
 */
@RestController
@RequestMapping("store")
public class StoreController {
    private final InMemoryKeyValueStore inMemoryKeyValueStore;
    private final StoreEntryMapping mapping;

    @Autowired
    public StoreController(InMemoryKeyValueStore inMemoryKeyValueStore, StoreEntryMapping storeEntryMapping) {
        this.inMemoryKeyValueStore = inMemoryKeyValueStore;
        mapping = storeEntryMapping;
    }

    /**
     * Get value by key.
     * @param key String
     * @return StoryEntry object
     */
    @GetMapping("{key}")
    public StoreEntryDTO getOne(@PathVariable String key) {
        return mapping.toDTO(inMemoryKeyValueStore.get(key));
    }

    /**
     * Add new key/value pair
     * @param entry input object
     * @return StoreEntry object
     */
    @PostMapping
    public StoreEntryDTO putOne(@RequestBody StoreEntryDTO entry) {
        StoreEntryEntity entity = mapping.toEntity(entry);

        return mapping.toDTO(inMemoryKeyValueStore.put(entity));
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
