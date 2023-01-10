package com.example.blokus.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class PieceTest {

    @Test
    public void getMaxXTest() {
        Assert.assertEquals(3, Piece.T.getMaxX());

    }

    @Test
    public void getMaxYTest() {
        Assert.assertEquals(2, Piece.T.getMaxY());
    }

    @Test
    public void rotateLeftTest() {
        Assert.assertEquals(Stream.of(
                        new Coordinate(0, 1)
                        , new Coordinate(0, 0)
                        , new Coordinate(1, 0)
                        , new Coordinate(2, 0)
                        , new Coordinate(3, 0)
                )
                .collect(Collectors.toList()), Piece.G2.rotateLeft());
    }
}