package com.example.playground.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;


@RunWith(MockitoJUnitRunner.class)
public class OccupiedBlockServiceTest {


    @InjectMocks
    private OccupiedBlockService occupiedBlockService;

    @Test
    public void occupiedBlockGeneratorTest() {

        OccupiedBlockService.Player dimitris = new OccupiedBlockService.Player("Dimitris", 'D');
        OccupiedBlockService.Player fotis = new OccupiedBlockService.Player("fotis", 'F');

        OccupiedBlockService.OccupiedBlock occupiedBlockExpected = new OccupiedBlockService.OccupiedBlock(
                Arrays.asList(
                        new OccupiedBlockService.Coordinate(1, 2)
                        , new OccupiedBlockService.Coordinate(2, 1)
                        , new OccupiedBlockService.Coordinate(2, 2)
                        , new OccupiedBlockService.Coordinate(3, 2)
                )
        );
        final OccupiedBlockService.OccupiedBlock occupiedBlock = occupiedBlockService.occupiedBlockGenerator(new OccupiedBlockService.Coordinate(1, 1), OccupiedBlockService.Piece.T);

        Assert.assertEquals(occupiedBlockExpected, occupiedBlock);

        OccupiedBlockService.OccupiedBlock occupiedBlockExpected2 = new OccupiedBlockService.OccupiedBlock(
                Arrays.asList(
                        new OccupiedBlockService.Coordinate(5, 6)
                        , new OccupiedBlockService.Coordinate(6, 5)
                        , new OccupiedBlockService.Coordinate(6, 6)
                        , new OccupiedBlockService.Coordinate(7, 6)
                )
        );
        final OccupiedBlockService.OccupiedBlock occupiedBlock2 = occupiedBlockService.occupiedBlockGenerator(new OccupiedBlockService.Coordinate(5, 5), OccupiedBlockService.Piece.T);

        Assert.assertEquals(occupiedBlockExpected2, occupiedBlock2);
    }

    @Test
    public void occupiedBlockGeneratorTestShowUI() {
        final OccupiedBlockService.Player dimitris = new OccupiedBlockService.Player("Dimitris", 'D');
        final OccupiedBlockService.Player fotis = new OccupiedBlockService.Player("fotis", 'F');

        final OccupiedBlockService.OccupiedBlock occupiedBlock = occupiedBlockService.occupiedBlockGenerator(new OccupiedBlockService.Coordinate(1, 1), OccupiedBlockService.Piece.T);

        final OccupiedBlockService.OccupiedBlock occupiedBlock2 = occupiedBlockService.occupiedBlockGenerator(new OccupiedBlockService.Coordinate(5, 5), OccupiedBlockService.Piece.T);

        final OccupiedBlockService.OccupiedBlock occupiedBlock3 = occupiedBlockService.occupiedBlockGenerator(new OccupiedBlockService.Coordinate(3, 3), OccupiedBlockService.Piece.I);

        dimitris.addOccupiedBlock(occupiedBlock);
        fotis.addOccupiedBlock(occupiedBlock2);
        dimitris.addOccupiedBlock(occupiedBlock3);

        OccupiedBlockService.Board board = new OccupiedBlockService.Board(20, 20, dimitris, fotis);

        occupiedBlockService.showCurrentBoardUI(board);
    }
}