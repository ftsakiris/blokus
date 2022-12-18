package com.example.playground.service;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OccupiedBlockService {

    public OccupiedBlock occupiedBlockGenerator(Player player, Coordinate coordinate, Piece piece) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final List<Coordinate> pieceCoordinates = new ArrayList<>(piece.coordinates);
        pieceCoordinates.forEach(a -> coordinates.add(new Coordinate(a.getX() + coordinate.getX(), a.getY() + coordinate.getY())));
        return new OccupiedBlock(coordinates, player);
    }

    public void showCurrentBoardUI(Board board) {
        final List<OccupiedBlock> occupiedBlocks = board.getOccupiedBlocks();

        for (Integer x = 0; x < board.getSizeX(); x++) {
            for (Integer y = 0; y < board.getSizeY(); y++) {
                boolean occupied = false;
                for (OccupiedBlock occupiedBlock : occupiedBlocks) {
                    if (occupied) {
                        break;
                    }
                    for (Coordinate coordinate : occupiedBlock.getCoordinateList()) {
                        if (coordinate.getX().equals(x) && coordinate.getY().equals(y)) {
                            System.out.print(" " + occupiedBlock.getPlayer().color + " ");
                            occupied = true;
                            break;
                        }
                    }
                }
                if (!occupied) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    @EqualsAndHashCode
    @ToString
    @Getter
    @AllArgsConstructor
    public static class OccupiedBlock {
        private List<Coordinate> coordinateList;
        private Player player;
    }

    @ToString
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    @Setter
    public static class Coordinate {
        private Integer x;
        private Integer y;
    }

    @AllArgsConstructor
    public enum Piece {

        T(Stream.of(
                        new Coordinate(0, 1)
                        , new Coordinate(1, 0)
                        , new Coordinate(1, 1)
                        , new Coordinate(2, 1)
                )
                .collect(Collectors.toList())),
        I(Stream.of(
                        new Coordinate(0, 1)
                        , new Coordinate(0, 2)
                        , new Coordinate(0, 3)
                        , new Coordinate(0, 4)
                        , new Coordinate(0, 5)
                )
                .collect(Collectors.toList()));

        private List<Coordinate> coordinates;

    }

    @ToString
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    public static class Board {
        private final Integer sizeX;
        private final Integer sizeY;
        List<OccupiedBlock> occupiedBlocks;

        public Board(Integer sizeX, Integer sizeY) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
        }
    }

    @ToString
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    public static class Player {
        private String name;
        private Character color;
    }
}
