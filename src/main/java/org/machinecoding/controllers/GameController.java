package org.machinecoding.controllers;

import org.machinecoding.commands.CommandFactory;
import org.machinecoding.commands.MoveCommand;
import org.machinecoding.exceptions.InvalidInputException;
import org.machinecoding.exceptions.InvalidMove;
import org.machinecoding.models.*;

public class GameController {
    private static final GameController instance = new GameController();

    private GameController() {}

    public Game initializeGame(Player player) {
        return new Game(player);
    }

    public static GameController getInstance() {
        return instance;
    }

    public void getBoard(Game game) {
        game.displayBoard();
    }

    // Update the board when player makes a move
    // 1. Merge the tiles together and calculate the score
    // 2. Add a random tile
    // 3. Display the Board
    // 4. Update the Game Status
    public void move(Game game, String dir) {
        try {
            Direction direction = getDirection(dir);
            MoveCommand moveCommand = CommandFactory.moveCommand(game, direction);
            moveCommand.execute();
            if (getGameStatus(game) == GameStatus.IN_PROGRESS) {
                game.addRandomTile();
                game.updateGameStatus();
            }
        }
        catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        catch (InvalidMove move) {
            System.out.println(move.getMessage());
        }
        finally {
            game.displayBoard();
        }
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    // Get valid Direction from the user input
    private Direction getDirection(String dir) throws InvalidInputException {
        return switch (dir) {
            case "LEFT" -> Direction.LEFT;
            case "RIGHT" -> Direction.RIGHT;
            case "UP" -> Direction.UP;
            case "DOWN" -> Direction.DOWN;
            default -> throw new InvalidInputException("Invalid Input: " + dir);
        };
    }
}
