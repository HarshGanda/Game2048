package org.machinecoding.commands;

import org.machinecoding.exceptions.InvalidMove;
import org.machinecoding.models.Cell;
import org.machinecoding.models.CellState;
import org.machinecoding.models.Game;

import java.util.ArrayList;
import java.util.List;

public class MoveLeftCommand implements MoveCommand {
    Game game;
    int points;
    boolean boardChanged;

    public MoveLeftCommand(Game game) {
        this.game = game;
        this.points = 0;
        boardChanged = false;
    }

    @Override
    public void execute() throws InvalidMove {
        compress();
        merge();
        if(boardChanged)
            compress();
        else
            throw new InvalidMove();
        if(points > 0)
            game.notifyObserver(points);
    }

    @Override
    public void merge() {
        List<List<Cell>> grid = game.getBoard().getGrid();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                int value = grid.get(i).get(j).getValue();
                if (grid.get(i).get(j).getCellState() != CellState.EMPTY &&
                        grid.get(i).get(j + 1).getValue() == value) {
                    grid.get(i).get(j).setValue(value * 2);
                    points += value * 2;
                    grid.get(i).get(j + 1).setValue(0);
                    grid.get(i).get(j + 1).setCellState(CellState.EMPTY);
                    boardChanged = true;
                }
            }
        }
    }

    @Override
    public void compress() {
        List<List<Cell>> grid = game.getBoard().getGrid();
        List<List<Cell>> newGrid = new ArrayList<>();
        initializeGrid(newGrid);
        for(int i = 0; i < 4; i++) {
            int pos = 0;
            for(int j = 0; j < 4; j++) {
                if(grid.get(i).get(j).getCellState() != CellState.EMPTY) {
                    int value = grid.get(i).get(j).getValue();
                    newGrid.get(i).get(pos).setValue(value);
                    newGrid.get(i).get(pos).setCellState(CellState.FILLED);
                    if(pos != j)
                        boardChanged = true;
                    pos++;
                }
            }
        }
        game.getBoard().setGrid(newGrid);
    }

}
