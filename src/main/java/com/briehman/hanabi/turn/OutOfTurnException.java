package com.briehman.hanabi.turn;


import com.briehman.hanabi.GameException;
import com.briehman.hanabi.Player;

public class OutOfTurnException extends GameException {
    private Player player;

    public OutOfTurnException(Player player) {
        super(String.format("Player %s attemped to play out of turn", player));
        this.player = player;
    }

    public Player player() { return player; }
}
