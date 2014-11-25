package com.briehman.hanabi;

public class Card {
    private final Color color;
    private final int num;

    public Card(Color color, int num) {
        this.color = color;
        this.num = num;
    }

    public Color color() { return color; }
    public int number() { return num; }
}
