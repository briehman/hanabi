package com.briehman.hanabi.turn;

import com.briehman.hanabi.*;
import com.briehman.hanabi.hint.Hint;
import com.briehman.hanabi.hint.OutOfHintsException;

import static com.briehman.hanabi.Action.HINT;

public class HintTurn extends Turn {
    private Hint hint;

    public HintTurn(Player player, Hint hint) {
        super(player, HINT);
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
        hint.send();
        g.useHint();
    }
}
