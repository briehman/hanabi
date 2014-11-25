package com.briehman.hanabi;

import static com.briehman.hanabi.Action.HINT;

public class HintTurn extends Turn {
    private Player player;
    private Hint hint;

    public HintTurn(Player player, Hint hint) {
        super(player, HINT);
        this.player = player;
        this.hint = hint;
    }

    public Hint hint() { return hint; }

    @Override
    public void validate(Game g) {
        if (g.getHintsLeft() == 0) {
            throw new OutOfHintsException();
        }
    }

    @Override
    public void complete(Game g) {
        g.useHint();
    }
}
