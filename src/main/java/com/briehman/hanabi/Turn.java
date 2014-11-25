package com.briehman.hanabi;

public abstract class Turn {
    private final Player player;
    private final Action action;

    public Turn(Player p, Action action) {
        this.player = p;
        this.action = action;
    }

    public Player getPlayer() {
        return player;
    }

    public Action getAction() {
        return action;
    }

    public abstract void validate(Game g);
    public abstract void complete(Game g);
}