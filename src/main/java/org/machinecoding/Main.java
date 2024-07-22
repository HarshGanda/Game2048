package org.machinecoding;

import org.machinecoding.controllers.GameController;
import org.machinecoding.controllers.ScoreController;
import org.machinecoding.models.Game;
import org.machinecoding.models.GameStatus;
import org.machinecoding.models.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize
        Scanner sc = new Scanner(System.in);
        GameController gameController = GameController.getInstance();
        ScoreController scoreController = new ScoreController();

        // Add player and Start Game
        System.out.println("Enter player name: ");
        String name = sc.nextLine();
        Player player = new Player(name);
        Game game = gameController.initializeGame(player);

        // Add ScoreController in the Observer List
        game.addObserver(scoreController);

        // Show Game Board
        System.out.println("Game Started!!");
        gameController.getBoard(game);

        // Make Moves and show Score
        while(gameController.getGameStatus(game) == GameStatus.IN_PROGRESS) {
            System.out.println("Make Move: UP, DOWN, LEFT or RIGHT");
            String dir = sc.nextLine();
            gameController.move(game, dir);
            player.setScore(scoreController.getScore());
            System.out.println("Score: " + player.getScore());
        }

        // Game Over - Show Result
        System.out.println("------ Game Over ------");
        System.out.println("Score: " + player.getScore());
    }
}