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

    // If
    // 1. any cell is empty
    // 2. any 2 cells can be merged
    // then game can continue
    // else Game Over
    public void updateGameStatus() {
        for(List<Cell> x : board.grid) {
            for(Cell y : x) {
                if(y.getCellState() == CellState.EMPTY)
                    return;
            }
        }

        setGameStatus(GameStatus.COMPLETED);
    }
}
