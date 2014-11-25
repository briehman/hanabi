package com.briehman.hanabi;

public abstract class Hint {
    protected Player sender;
    protected Player receiver;

    public Hint(Player sender, Player receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public Player sender() { return sender; }
    public Player receiver() { return receiver; }

    abstract public String type();

    @Override
    abstract public String toString();
}