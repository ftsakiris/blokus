package com.example.playground;

import com.example.playground.thread.SyncThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayGroundApplication {

    public static final String REST_PATH = "/api";

    public static void main(String[] args) {
//        runThread();
        SpringApplication.run(PlayGroundApplication.class, args);
    }

    private static void runThread() {
        final Object Lock1 = new Object();
        final Object Lock2 = new Object();
        SyncThread syncThread1 = new SyncThread("1", Lock1, Lock2);
        SyncThread syncThread2 = new SyncThread("2", Lock2, Lock1);
        syncThread1.start();
        syncThread2.start();
    }
}
