package yurij.study.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class StoreTTLExpiryService {
    @PostConstruct
    private void startThread() throws InterruptedException {
        Thread myThread = new Thread("MyThread");
        myThread.start();

        while (true) {
            myMethod();
        }
    }

    private void myMethod() {
        try{
            System.out.println("StoreTTLExpiryService iteration executed");
            Thread.sleep(5000);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
