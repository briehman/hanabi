package com.briehman.hanabi;

import com.briehman.hanabi.deck.Hand;

public class Player {
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() { return name; }
    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() { return name; }
}
