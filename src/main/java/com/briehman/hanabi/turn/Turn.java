package com.briehman.hanabi.turn;

import com.briehman.hanabi.Action;
import com.briehman.hanabi.Game;
import com.briehman.hanabi.Player;

public abstract class Turn {
    protected final Player player;
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