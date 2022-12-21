package com.example.blokus.service;

import com.example.blokus.domain.Board;
import com.example.blokus.domain.Coordinate;
import com.example.blokus.domain.OccupiedBlock;
import com.example.blokus.domain.Piece;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OccupiedBlockService {

    public OccupiedBlock occupiedBlockGenerator(Coordinate coordinate, Piece piece) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final List<Coordinate> pieceCoordinates = new ArrayList<>(piece.getCoordinates());
        pieceCoordinates.forEach(a -> coordinates.add(new Coordinate(a.getX() + coordinate.getX(), a.getY() + coordinate.getY())));
        return new OccupiedBlock(coordinates);
    }

    public List<Coordinate> findAvailableCoordinates(String playerName, Piece piece, Board board) {
        List<Coordinate> coordinates = new ArrayList<>();
        final List<OccupiedBlock> occupiedBlocks = board.getPlayer(playerName).getOccupiedBlocks();
        return coordinates;
    }

    public void showCurrentBoardUI(Board board) {
        final List<OccupiedBlock> occupiedBlocksA = board.getPlayerA().getOccupiedBlocks();
        final List<OccupiedBlock> occupiedBlocksB = board.getPlayerB().getOccupiedBlocks();

        for (Integer y = board.getSizeY() - 1; y >= 0; y--) {
            for (Integer x = 0; x < board.getSizeX(); x++) {
                boolean occupiedA = printPlayerOccupiedBlock(x, y, board.getPlayerA().getColor(), occupiedBlocksA);
                boolean occupiedB = printPlayerOccupiedBlock(x, y, board.getPlayerB().getColor(), occupiedBlocksB);
                if (!occupiedA && !occupiedB) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    private boolean printPlayerOccupiedBlock(Integer x, Integer y, Character color, List<OccupiedBlock> occupiedBlocksA) {
        for (OccupiedBlock occupiedBlock : occupiedBlocksA) {
            if (occupiedBlock.getCoordinateList().contains(new Coordinate(x, y))) {
                System.out.print(" " + color + " ");
                return true;
            }
        }
        return false;
    }

}
