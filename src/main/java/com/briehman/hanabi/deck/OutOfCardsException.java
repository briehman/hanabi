package com.briehman.hanabi.deck;

import com.briehman.hanabi.GameException;

public class OutOfCardsException extends GameException {
    public OutOfCardsException() {
        super("No more cards left in the deck!");
    }
}
