package org.machinecoding.exceptions;

public class InvalidMove extends Exception {
    public InvalidMove() {
        super("Move not possible");
    }
}
