package com.briehman.hanabi.turn;

import com.briehman.hanabi.*;
import com.briehman.hanabi.deck.Card;
import com.briehman.hanabi.deck.Hand;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscardTurnTest {
    private Game game;
    private Hand hand;
    private Player player;
    private final int handSize = 4;
    private Card toDiscard;
    private DiscardTurn turn;

    @Before
    public void setUp() {
        game = new Game();
        hand = game.getDeck().drawHand(handSize);
        player = new Player("Brian", hand);
        toDiscard = hand.getCard(0);
        turn = new DiscardTurn(player, toDiscard);
    }

    private void completeTurn() {
        turn.validate(game);
        turn.complete(game);
    }

    @Test
    public void playersDrawAfterDiscard() {
        completeTurn();
        assertFalse(hand.hasExactCard(toDiscard));
        assertEquals(handSize, player.getHand().size());
    }

    @Test
    public void playersCannotDrawAfterDiscardIfNoneLeft() {
        while (game.getDeck().hasCardLeft())
            game.getDeck().draw();

        completeTurn();
        assertFalse(hand.hasExactCard(toDiscard));
        assertEquals(handSize - 1, player.getHand().size());
    }

    @Test
    public void discardedCardsPlacedInDiscardPile() {
        completeTurn();
        assertTrue(game.getDiscardPile().contains(toDiscard));
    }

    @Test
    public void discardGainsHint() {
        int hints = game.getHintsLeft();
        game.useHint();
        completeTurn();
        assertEquals(hints, game.getHintsLeft());
    }

    @Test
    public void discardDoesNotExceedMaxHints() {
        int hints = game.getHintsLeft();
        completeTurn();
        assertEquals(hints, game.getHintsLeft());
    }
}