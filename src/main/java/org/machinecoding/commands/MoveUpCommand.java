package org.machinecoding.commands;

import org.machinecoding.exceptions.InvalidMove;
import org.machinecoding.models.Cell;
import org.machinecoding.models.CellState;
import org.machinecoding.models.Game;

import java.util.ArrayList;
import java.util.List;

public class MoveUpCommand implements MoveCommand {
    Game game;
    int points;
    boolean boardChanged;

    public MoveUpCommand(Game game) {
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
        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 3; i++) {
                int value = grid.get(i).get(j).getValue();
                if (grid.get(i).get(j).getCellState() != CellState.EMPTY &&
                        grid.get(i + 1).get(j).getValue() == value) {
                    grid.get(i).get(j).setValue(value * 2);
                    points += value * 2;
                    grid.get(i + 1).get(j).setValue(0);
                    grid.get(i + 1).get(j).setCellState(CellState.EMPTY);
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
        for(int j = 0; j < 4; j++) {
            int pos = 0;
            for(int i = 0; i < 4; i++) {
                if(grid.get(i).get(j).getCellState() != CellState.EMPTY) {
                    int value = grid.get(i).get(j).getValue();
                    newGrid.get(pos).get(j).setValue(value);
                    newGrid.get(pos).get(j).setCellState(CellState.FILLED);
                    if(pos != i)
                        boardChanged = true;
                    pos++;
                }
            }
        }
        game.getBoard().setGrid(newGrid);
    }

}
