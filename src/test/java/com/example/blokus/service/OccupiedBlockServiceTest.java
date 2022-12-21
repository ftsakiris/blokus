package com.example.blokus.service;

import com.example.blokus.domain.*;
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
    public void findAvailableCoordinatesTest() {
        final Player dimitris = new Player("Dimitris", 'D');
        final Player fotis = new Player("fotis", 'F');

        Board board = new Board(20, 20, dimitris, fotis);

        final OccupiedBlock occupiedBlock = occupiedBlockService.occupiedBlockGenerator(new Coordinate(0, 0), Piece.T);
        final OccupiedBlock occupiedBlock2 = occupiedBlockService.occupiedBlockGenerator(new Coordinate(board.getSizeX() - 3, board.getSizeY() - 2), Piece.T);
        final OccupiedBlock occupiedBlock3 = occupiedBlockService.occupiedBlockGenerator(new Coordinate(2, 3), Piece.I);

        dimitris.addOccupiedBlock(occupiedBlock);
        dimitris.addOccupiedBlock(occupiedBlock2);
        dimitris.addOccupiedBlock(occupiedBlock3);
        occupiedBlockService.showCurrentBoardUI(board);
    }

    @Test
    public void occupiedBlockGeneratorTest() {

        OccupiedBlock occupiedBlockExpected = new OccupiedBlock(
                Arrays.asList(
                        new Coordinate(1, 2)
                        , new Coordinate(2, 1)
                        , new Coordinate(2, 2)
                        , new Coordinate(3, 2)
                )
        );
        final OccupiedBlock occupiedBlock = occupiedBlockService.occupiedBlockGenerator(new Coordinate(1, 1), Piece.T);

        Assert.assertEquals(occupiedBlockExpected, occupiedBlock);

        OccupiedBlock occupiedBlockExpected2 = new OccupiedBlock(
                Arrays.asList(
                        new Coordinate(5, 6)
                        , new Coordinate(6, 5)
                        , new Coordinate(6, 6)
                        , new Coordinate(7, 6)
                )
        );
        final OccupiedBlock occupiedBlock2 = occupiedBlockService.occupiedBlockGenerator(new Coordinate(5, 5), Piece.T);

        Assert.assertEquals(occupiedBlockExpected2, occupiedBlock2);
    }

    @Test
    public void occupiedBlockGeneratorTestShowUI() {
        final Player dimitris = new Player("Dimitris", 'D');
        final Player fotis = new Player("fotis", 'F');

        final OccupiedBlock occupiedBlock = occupiedBlockService.occupiedBlockGenerator(new Coordinate(1, 1), Piece.T);

        final OccupiedBlock occupiedBlock2 = occupiedBlockService.occupiedBlockGenerator(new Coordinate(5, 5), Piece.T);

        final OccupiedBlock occupiedBlock3 = occupiedBlockService.occupiedBlockGenerator(new Coordinate(3, 3), Piece.I);

        dimitris.addOccupiedBlock(occupiedBlock);
        fotis.addOccupiedBlock(occupiedBlock2);
        dimitris.addOccupiedBlock(occupiedBlock3);

        Board board = new Board(20, 20, dimitris, fotis);

        occupiedBlockService.showCurrentBoardUI(board);
    }
}