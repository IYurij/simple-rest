package yurij.study.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Expiry entries service.
 */
@Service
public class StoreTTLExpiryService {
    /**
     * Thread sleep time.
     */
    private final long sleepTimeMillis;

    private final InMemoryKeyValueStore inMemoryKeyValueStore;

    /**
     * Thread running method.
     *
     * @throws InterruptedException thread interrupted
     */
    @PostConstruct
    private void startThread() throws InterruptedException {
        Thread myThread = new Thread(this::removeExpiredThread, "removeExpiredThread");
        myThread.start();
    }

    @Autowired
    public StoreTTLExpiryService(@Value("${store.ttl.expiry.sleep_time_ms}") long sleepTimeMillis,
                                 InMemoryKeyValueStore inMemoryKeyValueStore) {
        this.sleepTimeMillis = sleepTimeMillis;
        this.inMemoryKeyValueStore = inMemoryKeyValueStore;
        System.out.println(sleepTimeMillis);
    }

    /**
     * Thread method
     */
    private void removeExpiredThread() {
        try {
            while (true) {
                inMemoryKeyValueStore.removeExpiredEntries();

                Thread.sleep(sleepTimeMillis);
            }

        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
