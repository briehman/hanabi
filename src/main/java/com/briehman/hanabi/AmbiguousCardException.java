package com.briehman.hanabi;

public class AmbiguousCardException extends GameException {
    public AmbiguousCardException(Card c) {
        super("More than 1 card matching " + c + " found");
    }
}
