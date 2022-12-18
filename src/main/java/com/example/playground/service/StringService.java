package com.example.playground.service;

import org.springframework.stereotype.Service;

@Service
public class StringService {

    public String reverseString(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            result.insert(0, str.charAt(i));
        }
        return result.toString();
    }

    public boolean hasVowel(String str) {
        if (str == null) {
            return false;
        }
        return str.toLowerCase().matches(".*[aeiou].*");
    }

    public boolean isPalindromeString(String str) {
        if (str == null) {
            return false;
        }
        return str.equals(reverseString(str));
    }
}
