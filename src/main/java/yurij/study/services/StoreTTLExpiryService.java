package yurij.study.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Expiry entries service.
 */
@Service
public class StoreTTLExpiryService {
    /**
     * Thread sleep time.
     */
    public final int SLEEP_TIME = 100;

    private final InMemoryKeyValueStore inMemoryKeyValueStore;

    /**
     * Thread running method.
     *
     * @throws InterruptedException thread interrupted
     */
    @PostConstruct
    private void startThread() throws InterruptedException {
        Thread myThread = new Thread(this::removeExpiredThread,"removeExpiredThread");
        myThread.start();
    }

    @Autowired
    public StoreTTLExpiryService(InMemoryKeyValueStore inMemoryKeyValueStore) {
        this.inMemoryKeyValueStore = inMemoryKeyValueStore;
    }

    /**
     * Thread method
     */
    private void removeExpiredThread() {
        try{
            while (true) {
                inMemoryKeyValueStore.removeExpiredEntries();

                Thread.sleep(SLEEP_TIME);
            }

        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
