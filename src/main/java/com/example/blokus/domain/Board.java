package com.example.blokus.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class Board {
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

    public List<Coordinate> getOccupiedCoordinates() {
        final List<OccupiedBlock> occupiedBlocks = getOccupiedBlocks();
        List<Coordinate> coordinates = new ArrayList<>();
        occupiedBlocks.forEach(a -> coordinates.addAll(a.getCoordinateList()));
        return coordinates;
    }

    public boolean hasOccupiedBlocks() {
        return !getOccupiedBlocks().isEmpty();
    }

    public boolean isOccupiedCoordinate(Coordinate coordinate) {
        return getOccupiedCoordinates().contains(coordinate);
    }

    public boolean checkCrossForOccupiedCoordinates(Coordinate coordinate, String playerName) {
        final Player player = getPlayer(playerName);
        final boolean right = !player.hasOccupiedBlock(new Coordinate(coordinate.getX() + 1, coordinate.getY()));
        final boolean left = !player.hasOccupiedBlock(new Coordinate(coordinate.getX() - 1, coordinate.getY()));
        final boolean up = !player.hasOccupiedBlock(new Coordinate(coordinate.getX(), coordinate.getY() + 1));
        final boolean down = !player.hasOccupiedBlock(new Coordinate(coordinate.getX(), coordinate.getY() - 1));
        return right && left && up && down;
    }

    public boolean isValidCoordinate(Coordinate coordinate) {
        return !(
                coordinate == null
                        || coordinate.getX() <= 0
                        || coordinate.getY() <= 0
                        || coordinate.getX() > getSizeX() - 1
                        || coordinate.getY() > getSizeY() - 1
        );
    }
}
