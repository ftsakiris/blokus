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
public class Player {
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
