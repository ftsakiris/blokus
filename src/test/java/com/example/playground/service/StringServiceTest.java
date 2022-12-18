package com.example.playground.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StringServiceTest {

    @InjectMocks
    private StringService stringService;

    @Test
    public void reverseStringTest() {
        Assert.assertEquals("fotis", stringService.reverseString("sitof"));
        Assert.assertEquals("anna", stringService.reverseString("anna"));
        Assert.assertEquals("hgfdsa", stringService.reverseString("asdfgh"));
    }

    @Test
    public void hasVowelTest() {
        Assert.assertTrue(stringService.hasVowel("fotis"));
        Assert.assertTrue(stringService.hasVowel("Anna"));
        Assert.assertTrue(stringService.hasVowel("KAd"));
        Assert.assertFalse(stringService.hasVowel("TV"));
    }

    @Test
    public void isPalindromeStringTest() {
        Assert.assertFalse(stringService.isPalindromeString("fotis"));
        Assert.assertTrue(stringService.isPalindromeString("anna"));
        Assert.assertFalse(stringService.isPalindromeString("hgfdsa"));
    }
}