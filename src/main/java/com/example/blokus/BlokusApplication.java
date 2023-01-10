package com.example.blokus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlokusApplication {

    public static final String REST_PATH = "/api";

    public static void main(String[] args) {
        SpringApplication.run(BlokusApplication.class, args);
    }
}
