package com.example.blokus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Piece {

    T(Stream.of(
                    new Coordinate(0, 0)
                    , new Coordinate(1, -1)
                    , new Coordinate(1, 0)
                    , new Coordinate(2, 0)
            )
            .collect(Collectors.toList())),
    I(Stream.of(
                    new Coordinate(0, 0)
                    , new Coordinate(0, 1)
                    , new Coordinate(0, 2)
                    , new Coordinate(0, 3)
                    , new Coordinate(0, 4)
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

    public int getMaxX() {
        return getCoordinates().stream().mapToInt(Coordinate::getX).max().orElseThrow(NoSuchElementException::new) + 1;
    }

    public int getMaxY() {
        return getCoordinates().stream().mapToInt(Coordinate::getY).max().orElseThrow(NoSuchElementException::new) + 1;
    }

    //TODO fix this shit
    public List<Coordinate> rotateRight() {
        final List<Coordinate> coordinatesCurrent = getCoordinates();
        final List<Coordinate> coordinatesRotated = new ArrayList<>();
        for (Coordinate coordinate : coordinatesCurrent) {
            coordinatesRotated.add(new Coordinate(coordinate.getY(), coordinate.getX()));
        }
        return coordinatesRotated;
    }
}
