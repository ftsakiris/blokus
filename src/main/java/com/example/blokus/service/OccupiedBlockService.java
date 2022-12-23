package com.example.blokus.service;

import com.example.blokus.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OccupiedBlockService {

    public void move(String playerName, Piece piece, Coordinate coordinate, Board board) throws BlokusException {
        final Player player = board.getPlayer(playerName);
        if (player == null) {
            throw new BlokusException("Player does not exist");
        }
        final List<Coordinate> availableCoordinates = findAvailableCoordinates(playerName, piece, board);
        if (!availableCoordinates.contains(coordinate)) {
            throw new BlokusException("This coordinate is not available");
        }

        final List<OccupiedBlock> occupiedBlocks = occupiedPossibleBlocksGenerator(coordinate, piece, board);
        if (occupiedBlocks.isEmpty()) {
            throw new BlokusException("No available block");
        }
        player.addOccupiedBlock(occupiedBlocks.get(0));
    }

    public void move(String playerName, Piece piece, Board board) throws BlokusException {
        final Player player = board.getPlayer(playerName);
        if (player == null) {
            throw new BlokusException("Player does not exist");
        }
        final List<Coordinate> availableCoordinates = findAvailableCoordinates(playerName, piece, board);
        if (availableCoordinates.isEmpty()) {
            throw new BlokusException("No available coordinates for this Piece " + piece);
        }
        move(playerName, piece, availableCoordinates.get(0), board);
    }

    public List<OccupiedBlock> occupiedPossibleBlocksGenerator(Coordinate coordinate, Piece piece, Board board) {
        List<OccupiedBlock> occupiedBlocks = new ArrayList<>();
        for (int i = 0; i < piece.getMaxX(); i++) {
            final OccupiedBlock occupiedBlockLeft = occupiedBlockGenerator(new Coordinate(coordinate.getX() - i, coordinate.getY()), piece);
            if (board.isValidOccupiedBlock(occupiedBlockLeft)) {
                occupiedBlocks.add(occupiedBlockLeft);
            }
            final OccupiedBlock occupiedBlockRight = occupiedBlockGenerator(new Coordinate(coordinate.getX() - i, coordinate.getY()), piece);
            if (board.isValidOccupiedBlock(occupiedBlockRight)) {
                occupiedBlocks.add(occupiedBlockRight);
            }
        }
        for (int i = 0; i < piece.getMaxY(); i++) {
            final OccupiedBlock occupiedBlockDown = occupiedBlockGenerator(new Coordinate(coordinate.getX(), coordinate.getY() - i), piece);
            if (board.isValidOccupiedBlock(occupiedBlockDown)) {
                occupiedBlocks.add(occupiedBlockDown);
            }
            final OccupiedBlock occupiedBlockUp = occupiedBlockGenerator(new Coordinate(coordinate.getX(), coordinate.getY() + i), piece);
            if (board.isValidOccupiedBlock(occupiedBlockUp)) {
                occupiedBlocks.add(occupiedBlockUp);
            }
        }
        return occupiedBlocks;
    }

    public OccupiedBlock occupiedBlockGenerator(Coordinate coordinate, Piece piece) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final List<Coordinate> pieceCoordinates = new ArrayList<>(piece.getCoordinates());
        pieceCoordinates.forEach(a -> coordinates.add(new Coordinate(coordinate.getX() + a.getX(), coordinate.getY() + a.getY())));
        return new OccupiedBlock(coordinates);
    }

    public OccupiedBlock occupiedBlockGeneratorDiagonal(Coordinate coordinate, Piece piece) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final List<Coordinate> pieceCoordinates = new ArrayList<>(piece.getCoordinates());
        pieceCoordinates.forEach(a -> coordinates.add(new Coordinate(coordinate.getX() - piece.getMaxX() + 1 + a.getX(), coordinate.getY() - piece.getMaxY() + 1 + a.getY())));
        return new OccupiedBlock(coordinates);
    }

    public List<Coordinate> findAvailableCoordinates(String playerName, Piece piece, Board board) {
        List<Coordinate> resultCoordinates = new ArrayList<>();
        final Player player = board.getPlayer(playerName);
        final List<OccupiedBlock> occupiedBlocksOfCurrentPlayer = player.getOccupiedBlocks();

        // plays first
        final Coordinate coordinateFirstPlayer = firstMove(occupiedBlocksOfCurrentPlayer, piece, board);
        if (coordinateFirstPlayer != null) {
            resultCoordinates.add(coordinateFirstPlayer);
            return resultCoordinates;
        }
        // plays second
        final Coordinate coordinateSecondPlayer = secondMove(occupiedBlocksOfCurrentPlayer, piece, board);
        if (coordinateSecondPlayer != null) {
            resultCoordinates.add(coordinateSecondPlayer);
            return resultCoordinates;
        }

        addAllAvailableCoordinate(playerName, board, resultCoordinates, occupiedBlocksOfCurrentPlayer);

        return resultCoordinates;
    }

    private void addAllAvailableCoordinate(String playerName, Board board, List<Coordinate> resultCoordinates, List<OccupiedBlock> occupiedBlocksOfCurrentPlayer) {
        for (OccupiedBlock occupiedBlock : occupiedBlocksOfCurrentPlayer) {
            for (Coordinate coordinate : occupiedBlock.getCoordinateList()) {
                final Coordinate coordinateUpRight = new Coordinate(coordinate.getX() + 1, coordinate.getY() + 1);
                addNewAvailableCoordinate(board, coordinateUpRight, playerName, resultCoordinates);
                final Coordinate coordinateDownRight = new Coordinate(coordinate.getX() + 1, coordinate.getY() - 1);
                addNewAvailableCoordinate(board, coordinateDownRight, playerName, resultCoordinates);
                final Coordinate coordinateUpLeft = new Coordinate(coordinate.getX() - 1, coordinate.getY() + 1);
                addNewAvailableCoordinate(board, coordinateUpLeft, playerName, resultCoordinates);
                final Coordinate coordinateDownLeft = new Coordinate(coordinate.getX() - 1, coordinate.getY() - 1);
                addNewAvailableCoordinate(board, coordinateDownLeft, playerName, resultCoordinates);
            }
        }
    }

    private Coordinate firstMove(List<OccupiedBlock> occupiedBlocksOfCurrentPlayer, Piece piece, Board board) {
        if (occupiedBlocksOfCurrentPlayer.isEmpty() && !board.hasOccupiedBlocks()) {
            final Coordinate coordinate = new Coordinate(0, 0);
            final OccupiedBlock occupiedBlock = occupiedBlockGenerator(coordinate, piece);
            if (board.isValidOccupiedBlock(occupiedBlock) && occupiedBlock.contains(coordinate)) {
                return coordinate;
            }
        }
        return null;
    }

    private Coordinate secondMove(List<OccupiedBlock> occupiedBlocksOfCurrentPlayer, Piece piece, Board board) {
        if (occupiedBlocksOfCurrentPlayer.isEmpty() && board.hasOccupiedBlocks()) {
            final Coordinate coordinate = new Coordinate(board.getSizeX() - 1, board.getSizeY() - 1);
            final OccupiedBlock occupiedBlock = occupiedBlockGeneratorDiagonal(coordinate, piece);
            if (board.isValidOccupiedBlock(occupiedBlock) && occupiedBlock.contains(coordinate)) {
                return coordinate;
            }
        }
        return null;
    }

    private void addNewAvailableCoordinate(Board board, Coordinate coordinate, String playerName, List<Coordinate> resultCoordinates) {
        if (board.isValidCoordinate(coordinate) && !board.isOccupiedCoordinate(coordinate) && board.isValidCrossForOccupiedCoordinates(coordinate, playerName)) {
            resultCoordinates.add(coordinate);
        }
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
