package com.example.blokus.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PieceTest {

    @Test
    public void getMaxX() {
        Assert.assertEquals(3, Piece.T.getMaxX());

    }

    @Test
    public void getMaxY() {
        Assert.assertEquals(2, Piece.T.getMaxY());
    }
}