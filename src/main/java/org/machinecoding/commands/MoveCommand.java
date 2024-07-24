package org.machinecoding.commands;

import org.machinecoding.exceptions.InvalidMove;
import org.machinecoding.models.Cell;

import java.util.ArrayList;
import java.util.List;

public interface MoveCommand {
    void execute() throws InvalidMove;
    void merge();
    void compress();
    default void initializeGrid(List<List<Cell>> newGrid) {
        for(int i = 0; i < 4; i++) {
            newGrid.add(new ArrayList<>());
            for(int j = 0; j < 4; j++) {
                newGrid.get(i).add(new Cell(i, j));
            }
        }
    }
}
