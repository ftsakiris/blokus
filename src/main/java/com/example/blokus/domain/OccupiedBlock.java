package com.example.blokus.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public  class OccupiedBlock {
    private List<Coordinate> coordinateList;
}
