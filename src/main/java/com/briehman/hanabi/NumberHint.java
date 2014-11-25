package com.briehman.hanabi;

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

    @Override
    public String toString() {
        return String.format("-> %s: %s", receiver, type());
    }
}
