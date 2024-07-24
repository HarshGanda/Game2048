package org.machinecoding.commands;

import org.machinecoding.models.Direction;
import org.machinecoding.models.Game;

public class CommandFactory {
    public static MoveCommand moveCommand(Game game, Direction direction) {
        MoveCommand moveCommand;
        switch (direction) {
            case LEFT  : {  moveCommand = new MoveLeftCommand(game); return moveCommand; }
            case RIGHT : {  moveCommand = new MoveRightCommand(game); return moveCommand; }
            case UP    : {  moveCommand = new MoveUpCommand(game); return moveCommand; }
            case DOWN  : {  moveCommand = new MoveDownCommand(game); return moveCommand; }
            default : throw new IllegalArgumentException("Invalid Direction");
        }
    }
}
