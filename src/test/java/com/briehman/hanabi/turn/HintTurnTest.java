package com.briehman.hanabi.turn;

import com.briehman.hanabi.*;
import com.briehman.hanabi.hint.ColorHint;
import com.briehman.hanabi.hint.OutOfHintsException;
import org.junit.Before;
import org.junit.Test;

import static com.briehman.hanabi.Color.*;
import static org.junit.Assert.*;

public class HintTurnTest {
    private Game game;
    private Player player;
    private Player otherPlayer;

    @Before
    public void setUp() {
        game = new Game();
        player = new Player("Brian");
        otherPlayer = new Player("Katie");
    }

    @Test
    public void hintsGetUsedUp() {
        int numHints = game.getHintsLeft();
        Turn turn = new HintTurn(player, new ColorHint(otherPlayer, RED));
        turn.validate(game);
        turn.complete(game);
        assertEquals(numHints - 1, game.getHintsLeft());
    }

    @Test(expected=OutOfHintsException.class)
    public void cannotHintWhenOutOfHints() {
        while (game.getHintsLeft() > 0)
            game.useHint();

        Turn turn = new HintTurn(player, new ColorHint(otherPlayer, RED));
        turn.validate(game);
    }
}