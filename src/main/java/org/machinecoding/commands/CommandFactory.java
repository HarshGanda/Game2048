package org.machinecoding.commands;

import org.machinecoding.models.Direction;
import org.machinecoding.models.Game;

public class CommandFactory {
    public static void moveCommand(Game game, Direction direction) {
        switch (direction) {
            case LEFT  : { new MoveLeftCommand(game); return; }
            case RIGHT : { new MoveRightCommand(game); return; }
            case UP    : { new MoveUpCommand(game); return; }
            case DOWN  : { new MoveDownCommand(game); return; }
            default : throw new IllegalArgumentException("Invalid Direction");
        }
    }
}
