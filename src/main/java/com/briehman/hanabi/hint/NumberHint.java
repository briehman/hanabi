package com.briehman.hanabi.hint;

import com.briehman.hanabi.Player;
import com.briehman.hanabi.deck.Card;

public class NumberHint extends Hint {
    private String TYPE = "NUMBER";
    private int number;

    public NumberHint(Player receiver, int number) {
        super(receiver);
        this.number = number;
    }

    @Override
    public String type() {
        return TYPE;
    }

    public int number() { return number; }

    @Override
    public String toString() {
        return String.format("-> %s: %s", receiver, type());
    }

    @Override
    boolean matches(Card card) {
        return card.number() == number;
    }
}
