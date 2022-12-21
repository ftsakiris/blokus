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

    public boolean hasOccupiedBlocks() {
        return !getOccupiedBlocks().isEmpty();
    }
}
