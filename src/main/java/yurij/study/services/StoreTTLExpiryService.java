package yurij.study.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class StoreTTLExpiryService {
    public final int SLEEP_TIME = 100;

    private final InMemoryKeyValueStore inMemoryKeyValueStore;

    @PostConstruct
    private void startThread() throws InterruptedException {
        Thread myThread = new Thread(this::myMethod,"MyThread");
        myThread.start();
    }

    public StoreTTLExpiryService(InMemoryKeyValueStore inMemoryKeyValueStore) {
        this.inMemoryKeyValueStore = inMemoryKeyValueStore;
    }

    private void myMethod() {
        try{
            while (true) {
                inMemoryKeyValueStore.removeExpiredEntries();
                System.out.println("StoreTTLExpiryService iteration executed");
                Thread.sleep(SLEEP_TIME);
            }

        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
