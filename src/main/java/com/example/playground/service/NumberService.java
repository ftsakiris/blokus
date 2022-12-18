package com.example.playground.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class NumberService {

    public boolean isPrimeNumber(int number) {
        if (number == 0 || number == 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        for (int j = 2; j < number; j++) {
            if (number % j == 0) {
                return false;
            }
        }
        return true;
    }

    public String produceFibonacciNumbers(int number) {
        if (number == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder("0");
        int a = 0;
        int b = 1;
        int temp;
        for (int i = 0; i < number; i++) {
            result.append("," + (a + b));
            temp = a;
            a = b;
            b = temp + b;
        }
        return result.toString();
    }

    public boolean isOddNumber(int number) {
        if (number == 0) {
            return false;
        }
        return number % 2 != 0;
    }

    public LinkedList<Integer> descendingIterator(LinkedList<Integer> linkedList) {
        LinkedList<Integer> result = new LinkedList<>();
        linkedList.descendingIterator().forEachRemaining(result::add);
        return result;
    }

}
