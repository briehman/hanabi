package com.briehman.hanabi;

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
}
