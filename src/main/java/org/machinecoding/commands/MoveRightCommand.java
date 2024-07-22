package org.machinecoding.commands;

import org.machinecoding.models.Cell;
import org.machinecoding.models.CellState;
import org.machinecoding.models.Game;

import java.util.Stack;

public class MoveRightCommand implements MoveCommand {
    Game game;
    int points;
    Stack<Integer> st;
    public MoveRightCommand(Game game) {
        this.game = game;
        this.points = 0;
        this.st = new Stack<>();
        execute();
    }

    @Override
    public void execute() {
        for(int row = 0; row < 4; row++) {
            for(int col = 3; col >= 0; col--) {
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
            for(int col = 0; col < size; col++) {
                game.getBoard().getGrid().get(row).get(col).setCellState(CellState.EMPTY);
            }
            for(int col = size; col < 4; col++) {
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
