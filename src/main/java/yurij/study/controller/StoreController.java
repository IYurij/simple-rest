package yurij.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yurij.study.dto.StoreEntryRequestDTO;
import yurij.study.dto.StoreEntryResponseDTO;
import yurij.study.dto.mapping.StoreEntryMapping;
import yurij.study.entity.StoreEntryEntity;
import yurij.study.services.InMemoryKeyValueStore;

import java.util.ArrayList;
import java.util.List;

/**
 * API InMemoryStore controller class
 */
@RestController
@RequestMapping("store")
public class StoreController {
    private final InMemoryKeyValueStore inMemoryKeyValueStore;
    private final StoreEntryMapping storeEntryMapping;

    @Autowired
    public StoreController(InMemoryKeyValueStore inMemoryKeyValueStore, StoreEntryMapping storeEntryMapping) {
        this.inMemoryKeyValueStore = inMemoryKeyValueStore;
        this.storeEntryMapping = storeEntryMapping;
    }

    /**
     * Return all cached key/values.
     *
     * @return DTO's List
     */
    @GetMapping()
    public List<StoreEntryResponseDTO> getAll() {
        ArrayList<StoreEntryEntity> entitiesArrayList = inMemoryKeyValueStore.getAll();

        return storeEntryMapping.toDTOList(entitiesArrayList);
    }

    /**
     * Get value by key.
     *
     * @param key String
     * @return StoryEntry object
     */
    @GetMapping("{key}")
    public StoreEntryResponseDTO getOne(@PathVariable String key) {
        StoreEntryEntity entity = inMemoryKeyValueStore.get(key);

        return storeEntryMapping.toDTO(entity);
    }

    /**
     * Add new key/value pair.
     *
     * @param entry input object
     * @return StoreEntry object
     */
    @PostMapping
    public StoreEntryResponseDTO putOne(@RequestBody StoreEntryRequestDTO entry) {
        StoreEntryEntity entity = storeEntryMapping.toEntity(entry);

        return storeEntryMapping.toDTO(inMemoryKeyValueStore.put(entity));
    }

    /**
     * Delete Store data by key.
     *
     * @param key String
     */
    @DeleteMapping("{key}")
    public void remove(@PathVariable String key) {
        inMemoryKeyValueStore.remove(key);
    }
}
