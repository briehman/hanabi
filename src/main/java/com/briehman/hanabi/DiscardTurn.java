package com.briehman.hanabi;

import static com.briehman.hanabi.Action.DISCARD;

public class DiscardTurn extends Turn {
    public DiscardTurn(Player p) {
        super(p, DISCARD);
    }

    @Override
    public void validate(Game g) {}

    @Override
    public void complete(Game g) {
        // remove card from player
        // draw card for player
        // add hint
    }
}
