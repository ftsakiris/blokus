package com.example.playground.thread;

public class SyncThread extends Thread {

    private String name;
    private Object object1;
    private Object object2;

    public SyncThread(String name, Object object1, Object object2) {
        this.name = name;
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public void run() {
        synchronized (object1) {
            System.out.println("Thread " + name + ": Holding lock " + object1 + "...");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread " + name + ": Waiting for lock " + object1 + "...");

            synchronized (object2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread " + name + ": Holding lock 1 & 2...");
            }
        }

    }

}
