package com.briehman.hanabi;

public abstract class Hint {
    protected Player receiver;

    public Hint(Player receiver) {
        this.receiver = receiver;
    }

    public Player receiver() { return receiver; }

    abstract public String type();

    @Override
    abstract public String toString();
}