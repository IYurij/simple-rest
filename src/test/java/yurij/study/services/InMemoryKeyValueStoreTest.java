package yurij.study.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import yurij.study.entity.StoreEntryEntity;

class InMemoryKeyValueStoreTest {
    private InMemoryKeyValueStore store;
    @Value("${store.ttl.expiry.sleep_time_ms}")
    private long sleepTimeMills;

    @BeforeEach
    void setUp() {
        store = new InMemoryKeyValueStore();

        StoreEntryEntity entity = new StoreEntryEntity("test", "value", 100);
        StoreEntryEntity entity2 = new StoreEntryEntity("test2", "value2", 100);

        store.put(entity);
        store.put(entity2);
    }

    @Test
    void put_new_entry() {
        //Arrange
        StoreEntryEntity actual = new StoreEntryEntity("test3", "value3", 100);

        //Act
        StoreEntryEntity expected = store.put(actual);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void get_entry_by_key() {
        //Arrange
        String expectedEntryKey = "test";

        //Act
        StoreEntryEntity actual = store.get("test");

        //Assert
        Assertions.assertEquals(expectedEntryKey, "test");
    }

    @Test
    void remove_entry_by_key() {
        //Arrange
        int expectedEntriesCount = 1;

        //Act
        store.remove("test");
        int actualEntriesCount = store.getAll().size();

        //Assert
        Assertions.assertEquals(expectedEntriesCount, actualEntriesCount);
    }

    @Test
    void getAll_entries() {
        //Arrange
        int expectedEntriesCount = 2;

        //Act
        int actualEntriesCount = store.getAll().size();

        //Assert
        Assertions.assertEquals(expectedEntriesCount, actualEntriesCount);
    }

    @Test
    void removeExpiredEntries() throws InterruptedException {
        //Arrange
        int expectedEntriesCount = 0;
        long waitTime = sleepTimeMills * 2;

        //Act
        Thread.sleep(waitTime);
        store.removeExpiredEntries();

        //Assert
        Assertions.assertEquals(expectedEntriesCount, store.getAll().size());
    }
}