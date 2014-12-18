package com.briehman.hanabi.hint;

import com.briehman.hanabi.Player;
import com.briehman.hanabi.deck.Card;

public abstract class Hint {
    protected Player receiver;

    public Hint(Player receiver) {
        this.receiver = receiver;
    }

    public Player receiver() { return receiver; }

    abstract public String type();

    @Override
    abstract public String toString();

    abstract boolean matches(Card card);

    public void send() {
        receiver.getHand().addHint(this);
    }
}