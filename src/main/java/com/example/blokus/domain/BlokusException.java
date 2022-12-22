package com.example.blokus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlokusException extends Exception {

    private final String message;

}
