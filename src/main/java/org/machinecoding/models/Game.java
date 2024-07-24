package org.machinecoding.models;

import org.machinecoding.observer.GameObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    int id;
    Board board;
    GameStatus gameStatus;
    Player player;
    List<GameObserver> gameObserverList;

    // Start the game
    public Game(Player player) {
        id = 1;
        board = new Board();
        this.player = player;
        gameStatus = GameStatus.IN_PROGRESS;
        gameObserverList = new ArrayList<>();
        addRandomTile();
        addRandomTile();
    }

    public Board getBoard() {
        return this.board;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void displayBoard() {
        board.display();
    }

    public void addObserver(GameObserver gameObserver) {
        gameObserverList.add(gameObserver);
    }

    // Notify all observers in the Observer List
    public void notifyObserver(int points) {
        for(GameObserver gameObserver : gameObserverList) {
            gameObserver.update(points);
        }
    }

    // Find a random empty cell and fill a random value (2 or 4) in it
    public void addRandomTile() {
        if(checkEmptyCell() == false)
            return;
        Random random = new Random();
        int value = (random.nextInt(2) + 1) * 2;    // 2 or 4
        int row, col;
        do {
            row = random.nextInt(4);
            col = random.nextInt(4);
        } while(board.getGrid().get(row).get(col).getCellState().equals(CellState.FILLED));
        board.getGrid().get(row).get(col).setValue(value);
        board.getGrid().get(row).get(col).setCellState(CellState.FILLED);
    }

    // Check if there is at least 1 empty cell present in the grid
    public boolean checkEmptyCell() {
        for(List<Cell> x : board.getGrid())
            for(Cell y : x)
                if(y.getCellState() == CellState.EMPTY)
                    return true;
        return false;
    }

    // If no more moves left, then GameStatus = Completed
    public void updateGameStatus() {
        List<List<Cell>> x = board.getGrid();
        for(int i = 0; i < x.size(); i++) {
            List<Cell> y = x.get(i);
            for (int j = 0; j < y.size(); j++) {
                if(y.get(j).getCellState() == CellState.EMPTY) return;                          // Empty Cell is present
                int cellValue = y.get(j).getValue();
                if (i > 0 && x.get(i - 1).get(j).getValue() == cellValue) return;               // Top Cell Matches
                if (i < x.size() - 1 && x.get(i + 1).get(j).getValue() == cellValue) return;    // Bottom Cell Matches
                if (j > 0 && y.get(j - 1).getValue() == cellValue) return;                      // Left Cell Matches
                if (j < y.size() - 1 && y.get(j + 1).getValue() == cellValue) return;           // Right Cell Matches
            }
        }
        setGameStatus(GameStatus.COMPLETED);
    }

}
