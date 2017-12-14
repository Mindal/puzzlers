package ru.bogdanov.puzzlers.day14;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Square {
    private boolean isUsed;

    private int numberOfGroup;

    private List<Square> neighbours;

    public Square(boolean isUsed) {
        this.isUsed = isUsed;
        numberOfGroup = 0; //default value, means that no group is defined
        neighbours = new ArrayList<>();
    }

    @Override
    public String toString() {
        return isUsed ? "1" : "0";
    }
}
