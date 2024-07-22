package org.machinecoding.commands;

import org.machinecoding.models.Cell;
import org.machinecoding.models.CellState;
import org.machinecoding.models.Game;

import java.util.Stack;

public class MoveDownCommand implements MoveCommand {
    Game game;
    int points;
    Stack<Integer> st;

    public MoveDownCommand(Game game) {
        this.game = game;
        this.points = 0;
        this.st = new Stack<>();
        execute();
    }

    @Override
    public void execute() {
        for(int col = 0; col < 4; col++) {
            for(int row = 3; row >= 0; row--) {
                Cell cell = game.getBoard().getGrid().get(row).get(col);
                if(cell.getCellState() == CellState.FILLED) {
                    int cellValue = cell.getValue();
                    if(!st.empty() && st.peek() == cellValue) {
                        int score = 2 * cellValue;
                        points += score;
                        st.pop();
                        st.push(score);
                    }
                    else {
                        st.push(cellValue);
                    }
                }
            }
            int size = 4 - st.size();
            for(int row = 0; row < size; row++) {
                game.getBoard().getGrid().get(row).get(col).setCellState(CellState.EMPTY);
            }
            for(int row = size; row < 4; row++) {
                game.getBoard().getGrid().get(row).get(col).setCellState(CellState.FILLED);
                game.getBoard().getGrid().get(row).get(col).setValue(st.peek());
                st.pop();
            }
            st.clear();
        }
        if(points > 0)
            game.notifyObserver(points);
    }
}
