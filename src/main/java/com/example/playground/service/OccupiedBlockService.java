package com.example.playground.service;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OccupiedBlockService {

    public OccupiedBlock occupiedBlockGenerator(Coordinate coordinate, Piece piece) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final List<Coordinate> pieceCoordinates = new ArrayList<>(piece.coordinates);
        pieceCoordinates.forEach(a -> coordinates.add(new Coordinate(a.getX() + coordinate.getX(), a.getY() + coordinate.getY())));
        return new OccupiedBlock(coordinates);
    }

    public List<Coordinate> findAvailableCoordinates(String playerName, Piece piece, Board board) {
        List<Coordinate> coordinates = new ArrayList<>();
        final List<OccupiedBlock> occupiedBlocks = board.getPlayer(playerName).getOccupiedBlocks();
        if (occupiedBlocks.isEmpty()) {
            coordinates.add(new Coordinate(0, board.getSizeY() - 1));
            return coordinates;
        }
        if (occupiedBlocks.size() == 1) {
            coordinates.add(new Coordinate(board.getSizeX() - 1, 0));
            return coordinates;
        }
        return coordinates;
    }

    public void showCurrentBoardUI(Board board) {
        final List<OccupiedBlock> occupiedBlocksA = board.getPlayerA().getOccupiedBlocks();
        final List<OccupiedBlock> occupiedBlocksB = board.getPlayerB().getOccupiedBlocks();

        for (Integer x = board.getSizeX() - 1; x >= 0; x--) {
            for (Integer y = 0; y < board.getSizeY(); y++) {
                boolean occupied = false;
                for (OccupiedBlock occupiedBlock : occupiedBlocksA) {
                    if (occupied) {
                        break;
                    }
                    if (occupiedBlock.getCoordinateList().contains(new Coordinate(x, y))) {
                        System.out.print(" " + board.getPlayerA().color + " ");
                        occupied = true;
                        break;
                    }
                }
                for (OccupiedBlock occupiedBlock : occupiedBlocksB) {
                    if (occupied) {
                        break;
                    }
                    if (occupiedBlock.getCoordinateList().contains(new Coordinate(x, y))) {
                        System.out.print(" " + board.getPlayerB().color + " ");
                        occupied = true;
                        break;
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
                .collect(Collectors.toList())),
        G(Stream.of(
                        new Coordinate(0, 0)
                        , new Coordinate(1, 0)
                        , new Coordinate(0, 1)
                        , new Coordinate(0, 2)
                )
                .collect(Collectors.toList())),
        G2(Stream.of(
                        new Coordinate(0, 0)
                        , new Coordinate(1, 0)
                        , new Coordinate(1, 1)
                        , new Coordinate(1, 2)
                        , new Coordinate(1, 3)
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
        private final Player playerA;
        private final Player playerB;

        public Player getPlayer(String playerName) {
            if (playerA.getName().equals(playerName)) {
                return playerA;
            }
            if (playerB.getName().equals(playerName)) {
                return playerB;
            }
            return null;
        }

        public List<OccupiedBlock> getOccupiedBlocks() {
            List<OccupiedBlock> occupiedBlocks = new ArrayList<>();
            occupiedBlocks.addAll(getPlayerA().getOccupiedBlocks());
            occupiedBlocks.addAll(getPlayerB().getOccupiedBlocks());
            return occupiedBlocks;
        }

        public boolean hasOccupiedBlocks() {
            return !getOccupiedBlocks().isEmpty();
        }
    }

    @ToString
    @EqualsAndHashCode
    @Getter
    @AllArgsConstructor
    public static class Player {
        private String name;
        private Character color;
        private List<OccupiedBlock> occupiedBlocks;

        public Player(String name, Character color) {
            this.name = name;
            this.color = color;
            this.occupiedBlocks = new ArrayList<>();
        }

        public void addOccupiedBlock(OccupiedBlock occupiedBlock) {
            occupiedBlocks.add(occupiedBlock);
        }
    }
}
