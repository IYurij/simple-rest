package yurij.study.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import yurij.study.entity.StoreEntryEntity;

class InMemoryKeyValueStoreTest {
    private InMemoryKeyValueStore store;
    private StoreEntryEntity newEntry;
    @Value("${store.ttl.expiry.sleep_time_ms}")
    private long sleepTimeMills;

    @BeforeEach
    void setUp() {
        store = new InMemoryKeyValueStore();
        newEntry = new StoreEntryEntity("test3", "value3", 100);

        StoreEntryEntity entity = new StoreEntryEntity("test", "value", 100);
        StoreEntryEntity entity2 = new StoreEntryEntity("test2", "value2", 100);

        store.put(entity);
        store.put(entity2);
    }

    @Test
    @DisplayName("Put new entry")
    void putNewEntry() {
        //Arrange
        StoreEntryEntity actual = newEntry;

        //Act
        StoreEntryEntity expected = store.put(actual);

        //Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get entry by key")
    void getEntryByKey() {
        //Arrange
        String expectedEntryWithKey = "test";

        //Act
        StoreEntryEntity actual = store.get("test");

        //Assert
        Assertions.assertEquals(expectedEntryWithKey, actual.getKey());
    }

    @Test
    @DisplayName("Get entry by key and verify value")
    void getEntryByKeyAndVerifyValue() {
        //Arrange
        String expectedValue = "value3";

        //Act
        StoreEntryEntity actual = store.put(newEntry);

        //Assert
        Assertions.assertEquals(expectedValue, actual.getValue());
    }

    @Test
    @DisplayName("Get entry by key and verify expiry timestamp")
    void getEntryByKeyAndVerifyExpiry() {
        //Arrange
        long expectedExpiryTimestamp = 100;

        //Act
        StoreEntryEntity actual = store.put(newEntry);

        //Assert
        Assertions.assertEquals(expectedExpiryTimestamp, actual.getExpiryTimestamp());
    }

    @Test
    @DisplayName("Remove entry by key")
    void removeEntryByKey() {
        //Arrange
        int expectedEntriesCount = 1;

        //Act
        store.remove("test");
        int actualEntriesCount = store.getAll().size();

        //Assert
        Assertions.assertEquals(expectedEntriesCount, actualEntriesCount);
    }

    @Test
    @DisplayName("Get all entries")
    void getAllEntries() {
        //Arrange
        int expectedEntriesCount = 2;

        //Act
        int actualEntriesCount = store.getAll().size();

        //Assert
        Assertions.assertEquals(expectedEntriesCount, actualEntriesCount);
    }

    @Test
    @DisplayName("Remove expired entries")
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