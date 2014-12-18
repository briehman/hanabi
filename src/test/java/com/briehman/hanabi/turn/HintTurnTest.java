package com.briehman.hanabi.turn;

import com.briehman.hanabi.*;
import com.briehman.hanabi.deck.Card;
import com.briehman.hanabi.deck.Hand;
import com.briehman.hanabi.hint.ColorHint;
import com.briehman.hanabi.hint.Hint;
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
        player = new Player("Brian", new Hand(c(RED, 1), c(BLUE, 3), c(GREEN, 3), c(WHITE, 4), c(RED, 5)));
        otherPlayer = new Player("Katie", new Hand(c(RED, 1), c(BLUE, 3), c(GREEN, 3), c(WHITE, 4), c(RED, 5)));
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

    @Test
    public void hintsGetStoredOnCards() {

        Hint hint = new ColorHint(otherPlayer, RED);
        Turn turn = new HintTurn(player, hint);
        turn.validate(game);
        turn.complete(game);
        for (Card card : otherPlayer.getHand().cards()) {
            assertTrue(card.getHints().contains(hint));
        }
    }

    private Card c(Color color, int number) {
        return new Card(color, number);
    }
}