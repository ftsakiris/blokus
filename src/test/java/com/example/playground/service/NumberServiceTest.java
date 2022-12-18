package com.example.playground.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NumberServiceTest {

    @InjectMocks
    private NumberService numberService;

    @Test
    public void isPrimeNumberTest() {
        Assert.assertFalse(numberService.isPrimeNumber(1));
        Assert.assertTrue(numberService.isPrimeNumber(2));
        Assert.assertTrue(numberService.isPrimeNumber(3));
        Assert.assertFalse(numberService.isPrimeNumber(4));
        Assert.assertTrue(numberService.isPrimeNumber(5));
        Assert.assertFalse(numberService.isPrimeNumber(6));
        Assert.assertTrue(numberService.isPrimeNumber(7));
        Assert.assertFalse(numberService.isPrimeNumber(8));
        Assert.assertFalse(numberService.isPrimeNumber(9));
        Assert.assertFalse(numberService.isPrimeNumber(10));
        Assert.assertTrue(numberService.isPrimeNumber(17));
        Assert.assertTrue(numberService.isPrimeNumber(97));
        Assert.assertFalse(numberService.isPrimeNumber(100));
    }

    @Test
    public void produceFibonacciNumbersTest() {
        Assert.assertEquals("0", numberService.produceFibonacciNumbers(0));
        Assert.assertEquals("0,1", numberService.produceFibonacciNumbers(1));
        Assert.assertEquals("0,1,2", numberService.produceFibonacciNumbers(2));
        Assert.assertEquals("0,1,2,3", numberService.produceFibonacciNumbers(3));
        Assert.assertEquals("0,1,2,3,5,8,13,21,34", numberService.produceFibonacciNumbers(8));
        Assert.assertEquals("0,1,2,3,5,8,13,21,34,55,89,144,233", numberService.produceFibonacciNumbers(12));
    }

    @Test
    public void isOddNumberTest() {
        Assert.assertFalse(numberService.isOddNumber(0));
        Assert.assertTrue(numberService.isOddNumber(1));
        Assert.assertFalse(numberService.isOddNumber(2));
        Assert.assertTrue(numberService.isOddNumber(3));
    }
}