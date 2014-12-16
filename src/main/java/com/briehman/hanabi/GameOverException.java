package com.briehman.hanabi;

public class GameOverException extends GameException {
    public GameOverException() {
        super("Game over!");
    }
}